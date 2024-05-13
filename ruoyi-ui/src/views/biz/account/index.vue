<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-row :gutter="20">
        <!--部门数据-->
        <el-col :span="4" :xs="24">
          <div class="head-container">
            <el-input
              v-model="queryParams.uid"
              placeholder="用户uid"
              clearable
              size="small"
              prefix-icon="el-icon-search"
              style="margin-bottom: 20px"
            />
          </div>
        </el-col>
        <el-col :span="4" :xs="24">
          <div class="head-container">
            <el-input
              v-model="queryParams.email"
              placeholder="用户email"
              clearable
              size="small"
              prefix-icon="el-icon-search"
              style="margin-bottom: 20px"
            />
          </div>
        </el-col>
        <el-col :span="4" :xs="24">
          <div class="head-container">
            <el-input
              v-model="queryParams.randomString"
              placeholder="邀请码"
              clearable
              size="small"
              prefix-icon="el-icon-search"
              style="margin-bottom: 20px"
            />
          </div>
        </el-col>
        <el-col :span="4" :xs="24">
          <div class="head-container">
            <el-input
              v-model="queryParams.inviteCode"
              placeholder="邀请码查询我邀请的人"
              clearable
              size="small"
              prefix-icon="el-icon-search"
              style="margin-bottom: 20px"
            />
          </div>
        </el-col>


        <el-col :span="4" :xs="24">
          <div class="head-container">
            <el-input
              v-model="queryParams.address"
              placeholder="etherum wallet"
              clearable
              size="small"
              prefix-icon="el-icon-search"
              style="margin-bottom: 20px"
            />
          </div>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['biz:account:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['biz:account:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['biz:account:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['biz:account:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="accountList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="uid" align="center" prop="uid" />
      <el-table-column label="username" align="center" prop="username" />
      <el-table-column label="email" align="center" prop="email" />
      <el-table-column label="twitter handle" align="center" prop="accountTag"/>


      <el-table-column label="refererCompleteCount" align="center" prop="taskCount" />
      <el-table-column label="selfCompleteCount" align="center" prop="selfCount" />
      <el-table-column label="refererCount" align="center" prop="refererCount" />
        <el-table-column label="wallets" align="center" prop="address" />
      <el-table-column label="inviteCode" align="center" prop="randomString"/>
      <el-table-column label="parent" align="center" prop="parent"/>
      <el-table-column label="path" align="center" prop="paths"/>
      <el-table-column label="hasSecondCoupon" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.selfCompleteCount === 6 ? "是" : "否" }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="blockusSuiWallet" align="center" prop="blockusSuiWallet" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['biz:account:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['biz:account:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改账户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="uid" prop="uid">
          <el-input v-model="form.uid" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="email" prop="email">
          <el-input v-model="form.email" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="address" prop="wallets">
          <el-input v-model="form.address" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="inviteCode" prop="randomString">
          <el-input v-model="form.randomString" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="twitter handle" prop="accountTag">
          <el-input v-model="form.accountTag" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="blocusToken" :visible.sync="bopen" width="500px" append-to-body>
      <div>
        {{blocusToken}}
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAccount, getAccount, delAccount, addAccount, updateAccount, getBlockToken } from "@/api/biz/account";

export default {
  name: "Account",
  data() {
    return {
      // 遮罩层
      loading: true,
      bopen: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 账户表格数据
      accountList: [],
      blocusToken: "",
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        uid: null,
        email: null,
        address: null,
        inviteCode: null,
        blockusSuiWallet: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        newsLetterSubscription: [
          { required: true, message: "$comment不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询账户列表 */
    getList() {
      this.loading = true;
      listAccount(this.queryParams).then(response => {
        this.accountList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        uid: null,
        username: null,
        email: null,
        isEmailVerified: null,
        registerTimestamp: null,
        randomString: null,
        passwordHash: null,
        newsLetterSubscription: null,
        avatar: null,
        thirdPartyLoginField: null,
        registerType: null,
        referrer: null,
        address: null,
        socialAccountGoogle: null,
        isOldAccount: null,
        accountTag: null,
        gamePackageId: null,
        blockusSuiWallet: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    handleToken(row){

       let that=this;
      getBlockToken(row.email).then(token=>{
        that.blocusToken=token
        this.bopen=true;
      }).catch(()=>{
        that.blocusToken="获取失败"
      })
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加账户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAccount(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改账户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAccount(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAccount(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除账户编号为"' + ids + '"的数据项？').then(function() {
        return delAccount(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('biz/account/export', {
        ...this.queryParams
      }, `account_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
