<template>
  <div class="app-container">

    <el-row>
      <el-col :span="24" class="card-box">
        <el-card>
          <div slot="header"><span><i class="el-icon-cpu"></i> 总览</span></div>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%;">
              <thead>
              <tr>
                <th class="el-table__cell is-leaf"><div class="cell">类型</div></th>
                <th class="el-table__cell is-leaf"  colspan="4"><div width="120px" class="cell">创建</div></th>
                <th class="el-table__cell is-leaf"  colspan="4"><div width="120px" class="cell">交易</div></th>
              </tr>
              </thead>
              <tbody   v-if="statictis&&statictis.totalStatu">
              <tr v-for="item in statictis.totalStatu">
                <td class="el-table__cell is-leaf"><div class="cell">{{item.coinType}}</div></td>
                <td class="el-table__cell is-leaf"  colspan="4"><div class="cell">{{item.create}}</div></td>
                <td class="el-table__cell is-leaf"  colspan="4" ><div class="cell">{{item.transfer}}</div></td>
              </tr>
              </tbody>
            </table>
          </div>
        </el-card>
      </el-col>

    </el-row>
    <el-row>
      <el-col :span="24" class="card-box">
        <el-card>
          <div slot="header"><span><i class="el-icon-cpu"></i> 每日碎片挖矿</span></div>
          <div class="el-table el-table--enable-row-hover el-table--medium">



            <table cellspacing="0" style="width: 100%;">
              <thead  v-if="statictis&&statictis.shredMintByDay">

              <tr >
                <th v-for="item in statictis.shredMintByDay" class="el-table__cell is-leaf" ><div width="120px" class="cell">{{item.timeWindow}}</div></th>
              </tr>
              </thead>
              <tbody   v-if="statictis&&statictis.shredMintByDay">
              <tr>
                <td v-for="item in statictis.shredMintByDay" class="el-table__cell is-leaf"><div class="cell">{{item.total}}</div></td>
              </tr>
              </tbody>
            </table>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" class="card-box">
        <el-card>
          <div slot="header"><span><i class="el-icon-cpu"></i> 每日碎片交易</span></div>
          <div class="el-table el-table--enable-row-hover el-table--medium">



            <table cellspacing="0" style="width: 100%;">
              <thead  v-if="statictis&&statictis.shredTxByDay">

              <tr >
                <th v-for="item in statictis.shredTxByDay" class="el-table__cell is-leaf" ><div width="120px" class="cell">{{item.timeWindow}}</div></th>
              </tr>
              </thead>
              <tbody   v-if="statictis&&statictis.shredTxByDay">
              <tr>
                <td v-for="item in statictis.shredTxByDay" class="el-table__cell is-leaf"><div class="cell">{{item.total}}</div></td>
              </tr>
              </tbody>
            </table>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" class="card-box">
        <el-card>
          <div slot="header"><span><i class="el-icon-cpu"></i> 每日武器交易</span></div>
          <div class="el-table el-table--enable-row-hover el-table--medium">



            <table cellspacing="0" style="width: 100%;">
              <thead  v-if="statictis&&statictis.swordMintByDay">

              <tr >
                <th v-for="item in statictis.swordMintByDay" class="el-table__cell is-leaf" ><div width="120px" class="cell">{{item.timeWindow}}</div></th>
              </tr>
              </thead>
              <tbody   v-if="statictis&&statictis.swordMintByDay">
              <tr>
                <td v-for="item in statictis.swordMintByDay" class="el-table__cell is-leaf"><div class="cell">{{item.total}}</div></td>
              </tr>
              </tbody>
            </table>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" class="card-box">
        <el-card>
          <div slot="header"><span><i class="el-icon-cpu"></i> 每日武器挖矿</span></div>
          <div class="el-table el-table--enable-row-hover el-table--medium">



            <table cellspacing="0" style="width: 100%;">
              <thead  v-if="statictis&&statictis.swordTxByDay">

              <tr >
                <th v-for="item in statictis.swordTxByDay" class="el-table__cell is-leaf" ><div width="120px" class="cell">{{item.timeWindow}}</div></th>
              </tr>
              </thead>
              <tbody   v-if="statictis&&statictis.swordTxByDay">
              <tr>
                <td v-for="item in statictis.swordTxByDay" class="el-table__cell is-leaf"><div class="cell">{{item.total}}</div></td>
              </tr>
              </tbody>
            </table>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" class="card-box">
        <el-card>
          <div slot="header"><span><i class="el-icon-cpu"></i> top20地址碎片</span></div>
          <div class="el-table el-table--enable-row-hover el-table--medium">
    <el-table
      v-loading="loading"
      :data="statictis.rank"
      style="width: 100%;"
    >
      <el-table-column label="账户" align="center" prop="receiver" :show-overflow-tooltip="true" />
      <el-table-column label="持有数量" align="center" prop="total" :show-overflow-tooltip="true" />

    </el-table>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { list, forceLogout } from "@/api/monitor/online";
import { getStaticNft } from "@/api/biz/nft";

export default {
  name: "Online",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      statictis: {
        totalStatu: [],
        rank: [],
        shredMintByDay: [],
        shredTxByDay:[],
        swordMintByDay:[],
        swordTxByDay:[],
      },
      // 表格数据
      list: [],
      pageNum: 1,
      pageSize: 10,
      // 查询参数
      queryParams: {
        ipaddr: undefined,
        userName: undefined
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询登录日志列表 */
    getList() {
      this.loading = true;
      list(this.queryParams).then(response => {
        this.list = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      getStaticNft().then(data => {
        this.statictis = data;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 强退按钮操作 */
    handleForceLogout(row) {
      this.$modal.confirm('是否确认强退名称为"' + row.userName + '"的用户？').then(function() {
        return forceLogout(row.tokenId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("强退成功");
      }).catch(() => {});
    }
  }
};
</script>

