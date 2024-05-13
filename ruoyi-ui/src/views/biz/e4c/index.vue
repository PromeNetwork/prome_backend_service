<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="e4c大于" prop="e4c">
        <el-input
          v-model="queryParams.beginE4c"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="e4c小于" prop="e4c">
        <el-input
          v-model="queryParams.endE4c"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="接收人" prop="recipient">
        <el-input
          v-model="queryParams.recipient"
          placeholder="请输入接收人"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
      <el-form-item label="描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务id" prop="txId">
        <el-input
          v-model="queryParams.txId"
          placeholder="请输入业务唯一id"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['biz:e4c:add']"
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
          v-hasPermi="['biz:e4c:edit']"
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
          v-hasPermi="['biz:e4c:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['biz:e4c:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="e4cList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="数量" align="center" prop="e4c">
      </el-table-column>
      <el-table-column label="接收人" align="center" prop="recipient">
      </el-table-column>
      <el-table-column label="时间" align="center" prop="timestamp" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.timestamp, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
<!--          <span>{{ scope.row.timestamp }}</span>-->
        </template>

      </el-table-column>
      <el-table-column label="描述" align="center" prop="description">
      </el-table-column>
      <el-table-column label="标签" align="center" prop="tag">
      </el-table-column>
      <el-table-column label="业务唯一id" align="center" prop="txId">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['biz:e4c:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['biz:e4c:remove']"
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

    <!-- 添加或修改e4c对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="数量" prop="e4c">
          <el-input v-model="form.e4c" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="接收人" prop="recipient">
          <el-input v-model="form.recipient" placeholder="请输入接收人" />
        </el-form-item>
        <el-form-item label="时间" prop="timestamp">
          <el-date-picker clearable
            v-model="form.timestamp"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="标签" prop="tag">
          <el-input v-model="form.tag" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="业务唯一id" prop="txId">
          <el-input v-model="form.txId" placeholder="请输入业务唯一id" />
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
import { listE4c, getE4c, delE4c, addE4c, updateE4c } from "@/api/biz/e4c";

export default {
  name: "E4c",
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
      // e4c表格数据
      e4cList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      datarangeE4c:[],
      // 业务唯一id时间范围
      daterangeTimestamp: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        beginE4c: null,
        endE4c: null,
        e4c: null,
        recipient: null,
        timestamp: null,
        description: null,
        txId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        recipient: [
          { required: true, message: "接收人不能为空", trigger: "blur" }
        ],
        timestamp: [
          { required: true, message: "时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询e4c列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeTimestamp && '' != this.daterangeTimestamp) {
        this.queryParams.params["beginTimestamp"] = new Date(this.daterangeTimestamp[0]).getTime();
        this.queryParams.params["endTimestamp"] =  new Date(this.daterangeTimestamp[1]).getTime();

      }
      if(null != this.queryParams.beginE4c){
        this.queryParams.params["beginE4c"] = this.queryParams.beginE4c;
      }
      if(null != this.queryParams.endE4c){
        this.queryParams.params["endE4c"] = this.queryParams.endE4c;
      }
      listE4c(this.queryParams).then(response => {
        this.e4cList = response.rows;
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
        e4c: null,
        recipient: null,
        timestamp: null,
        description: null,
        tag: null,
        txId: null
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
      this.daterangeTimestamp = [];
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
      this.title = "添加e4c";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getE4c(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改e4c";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateE4c(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addE4c(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除e4c编号为"' + ids + '"的数据项？').then(function() {
        return delE4c(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('biz/e4c/export', {
        ...this.queryParams
      }, `e4c_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
