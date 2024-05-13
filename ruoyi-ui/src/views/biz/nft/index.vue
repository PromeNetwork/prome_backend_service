<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="数量大于" prop="e4c">
        <el-input
          v-model="queryParams.beginAmount"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量小于" prop="e4c">
        <el-input
          v-model="queryParams.endAmount"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="coinType" prop="cointype">
        <el-select v-model="queryParams.cointype" placeholder="cointype" clearable>
          <el-option
            v-for="dict in dict.type.coin_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="txtype" prop="txtype">
        <el-select v-model="queryParams.txtype" placeholder="txtype" clearable>
          <el-option
            v-for="dict in  ['create', 'transfer']"
            :key="dict"
            :label="dict"
            :value="dict"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="时间">
        <el-date-picker
          v-model="daterangeTimestamp"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
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
          v-hasPermi="['biz:nft:add']"
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
          v-hasPermi="['biz:nft:edit']"
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
          v-hasPermi="['biz:nft:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['biz:nft:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="nftList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="sender" align="center" prop="sender">
      </el-table-column>
      <el-table-column label="receiver" align="center" prop="receiver">
      </el-table-column>
      <el-table-column label="amount" align="center" prop="amount">
      </el-table-column>
      <el-table-column label="txtype" align="center" prop="txtype">
      </el-table-column>
      <el-table-column label="digest" align="center" prop="digest">
      </el-table-column>
      <el-table-column label="checkpoint" align="center" prop="checkpoint">
      </el-table-column>
      <el-table-column label="cointype" align="center" prop="cointype">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.coin_type" :value="scope.row.cointype"/>
        </template>
      </el-table-column>
      <el-table-column label="objectid" align="center" prop="objectid">
      </el-table-column>
      <el-table-column label="time" align="center" prop="timestamp">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.timestamp, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
          <!--          <span>{{ scope.row.timestamp }}</span>-->
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['biz:nft:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['biz:nft:remove']"
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

    <!-- 添加或修改nft对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="sender" prop="sender">
          <el-input v-model="form.sender" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="receiver" prop="receiver">
          <el-input v-model="form.receiver" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="amount" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入amount" />
        </el-form-item>
          <el-form-item label="txtype" prop="txtype">
            <el-input v-model="form.txtype" placeholder="txtype" />
          </el-form-item>
        <el-form-item label="digest" prop="digest">
          <el-input v-model="form.digest" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="checkpoint" prop="checkpoint">
          <el-input v-model="form.checkpoint" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="cointype" prop="cointype">
          <el-select v-model="form.cointype" placeholder="请选择cointype">
            <el-option
              v-for="dict in dict.type.coin_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="objectid" prop="objectid">
          <el-input v-model="form.objectid" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="timestamp" prop="timestamp">
          <el-input v-model="form.timestamp" placeholder="请输入timestamp" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listNft, getNft, delNft, addNft, updateNft } from "@/api/biz/nft";

export default {
  name: "Nft",
  dicts: ['coin_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
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
      // nft表格数据
      nftList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      daterangeTimestamp:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sender: null,
        receiver: null,
        amount: null,
        txtype: null,
        digest: null,
        checkpoint: null,
        cointype: null,
        objectid: null,
        timestamp: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sender: [
          { required: true, message: "sender不能为空", trigger: "blur" }
        ],
        receiver: [
          { required: true, message: "receiver不能为空", trigger: "blur" }
        ],
        amount: [
          { required: true, message: "amount不能为空", trigger: "blur" }
        ],
        txtype: [
          { required: true, message: "txtype不能为空", trigger: "blur" }
        ],
        digest: [
          { required: true, message: "digest不能为空", trigger: "blur" }
        ],
        checkpoint: [
          { required: true, message: "$comment不能为空", trigger: "blur" }
        ],
        cointype: [
          { required: true, message: "$comment不能为空", trigger: "change" }
        ],
        timestamp: [
          { required: true, message: "$comment不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询nft列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      debugger
      if(this.queryParams.beginAmount) {
        this.queryParams.params['beginAmount'] = this.queryParams.beginAmount
        delete this.queryParams.beginAmount
      }
      if(this.queryParams.endAmount) {
        this.queryParams.params['endAmount'] = this.queryParams.endAmount
        delete this.queryParams.endAmount
      }
      if (null != this.daterangeTimestamp && '' != this.daterangeTimestamp) {
        this.queryParams.params["beginTimestamp"] = new Date(this.daterangeTimestamp[0]).getTime();
        this.queryParams.params["endTimestamp"] =  new Date(this.daterangeTimestamp[1]).getTime();

      }
      listNft(this.queryParams).then(response => {
        this.nftList = response.rows;
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
        sender: null,
        receiver: null,
        amount: null,
        txtype: [],
        digest: null,
        checkpoint: null,
        cointype: null,
        objectid: null,
        timestamp: null
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加nft";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getNft(id).then(response => {
        this.form = response.data;
        this.form.txtype = this.form.txtype.split(",");
        this.open = true;
        this.title = "修改nft";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.txtype = this.form.txtype.join(",");
          if (this.form.id != null) {
            updateNft(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addNft(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除nft编号为"' + ids + '"的数据项？').then(function() {
        return delNft(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('biz/nft/export', {
        ...this.queryParams
      }, `nft_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
