<template>
  <div class="pos">
    <div>
      <el-row>
        <el-col :span="7" id="order-list" class="pos-order">
          <el-tabs>
            <el-tab-pane label="点餐">
              <el-table :data="tableData" border show-summary style="width: 100%">
                <el-table-column prop="goodsName" label="商品"></el-table-column>
                <el-table-column prop="count" label="量" width="50"></el-table-column>
                <el-table-column prop="price" label="金额" width="70"></el-table-column>
                <el-table-column label="操作" width="100" fixed="right">
                  <template slot-scope="scope">
                    <el-button type="text" size="small" @click="delSingleGoods(scope.row)">删除</el-button>
                    <el-button type="text" size="small" @click="addOrderList(scope.row)">增加</el-button>
                  </template>
                </el-table-column>
              </el-table>

              <div class="order_btn">
                <el-button type="warning">挂单</el-button>
                <el-button type="danger" @click="delAllGoods">删除</el-button>
                <el-button type="success" @click="checkout">结账</el-button>
              </div>

            </el-tab-pane>
            <el-tab-pane label="挂单">
              挂单
            </el-tab-pane>
            <el-tab-pane label="外卖">
              外卖
            </el-tab-pane>
          </el-tabs>

        </el-col>
        <el-col :span="17">
          <!--商品展示-->
          <div class="often-goods">
            <div class="title">常用商品</div>
            <div class="often-goods-list">
              <ul>
                <li v-for="goods in oftenGoods" @click="addOrderList(goods)">
                  <span>{{goods.goodsName}}</span>
                  <span class="o-price">￥{{goods.price}}元</span>
                </li>
              </ul>
            </div>
          </div>

          <!--商品分类-->
          <div class="goods-type">
            <el-tabs>
              <el-tab-pane label="汉堡">
                <ul class="cookList">
                  <li v-for="goods in type0Goods" @click="addOrderList(goods)">
                    <span class="foodImg">
                      <img
                        src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2662577439,1853685025&fm=27&gp=0.jpg"
                        width="100%">
                    </span>
                    <span class="foodName">{{goods.goodsName}}</span>
                    <span class="foodPrice">￥{{goods.price}}元</span>
                  </li>
                </ul>
              </el-tab-pane>
              <el-tab-pane label="小食">
                <ul class="cookList">
                  <li v-for="goods in type1Goods" @click="addOrderList(goods)">
                    <span class="foodImg">
                      <img
                        src="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3140282935,1281002458&fm=26&gp=0.jpg"
                        width="100%">
                    </span>
                    <span class="foodName">{{goods.goodsName}}</span>
                    <span class="foodPrice">￥{{goods.price}}元</span>
                  </li>
                </ul>
              </el-tab-pane>
              <el-tab-pane label="饮料">
                <ul class="cookList">
                  <li v-for="goods in type2Goods" @click="addOrderList(goods)">
                    <span class="foodImg">
                      <img
                        src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1302761685,1513557671&fm=26&gp=0.jpg"
                        width="100%">
                    </span>
                    <span class="foodName">{{goods.goodsName}}</span>
                    <span class="foodPrice">￥{{goods.price}}元</span>
                  </li>
                </ul>
              </el-tab-pane>
              <el-tab-pane label="套餐">
                <ul class="cookList">
                  <li v-for="goods in type3Goods" @click="addOrderList(goods)">
                    <span class="foodImg">
                      <img
                        src="https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=278867736,577352891&fm=26&gp=0.jpg"
                        width="100%">
                    </span>
                    <span class="foodName">{{goods.goodsName}}</span>
                    <span class="foodPrice">￥{{goods.price}}元</span>
                  </li>
                </ul>
              </el-tab-pane>
            </el-tabs>
          </div>

        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: "Pos",
    data() {
      return {
        tableData: [],
        oftenGoods: [],
        type0Goods: [],
        type1Goods: [],
        type2Goods: [],
        type3Goods: [],
        totalMoney: 0, //订单总价格
        totalCount: 0  //订单商品总数量

      }
    },
    methods: {
      //添加订单列表的方法
      addOrderList(goods) {
        this.totalCount = 0;
        this.totalMoney = 0;
        let isHave = false;
        for (let i = 0; i < this.tableData.length; i++) {
          if (this.tableData[i].goodsId == goods.goodsId) {
            isHave = true;
          }
        }

        if (isHave) {
          //存在 找到该商品 就进行数量添加
          let arr = this.tableData.filter(o => o.goodsId == goods.goodsId);
          arr[0].count++;
        } else {
          //不存在就推入数组
          let newGoods = {
            goodsId: goods.goodsId,
            goodsName: goods.goodsName,
            price: goods.price,
            count: 1,
          };
          this.tableData.push(newGoods)
        }
        this.getAllMoney();

      },
      //汇总数量和金额
      getAllMoney(){
        this.totalCount = 0;
        this.totalMoney = 0;
        if (this.tableData){
          this.tableData.forEach(element => {
            //计算总件数
            this.totalCount += element.count;
            //计算总价格
            this.totalMoney = this.totalMoney + (element.price * element.count);
          });
        }
      },
      //删除单个商品
      delSingleGoods(goods){
        //筛选出不是该商品的数组
        this.tableData = this.tableData.filter(o => o.goodsId != goods.goodsId);
        //重新计算价格
        this.getAllMoney();
      },
      //删除所有商品
      delAllGoods(){
        this.tableData = [];
        this.totalCount = 0;
        this.totalMoney = 0;
      },
      //结账
      checkout(){
        if(this.totalCount != 0){
          this.$message({
            message: '结账成功，支付'+'￥'+ this.totalMoney + '元，' + ' 感谢你又为店里出了一份力!',
            type: 'success'
          });
          this.tableData = [];
          this.totalCount = 0;
          this.totalMoney = 0;
        } else {
          this.$message.error('不能空结。老板了解你急切的心情！');
        }
      },

    },
    mounted() {
      var orderHeight = document.body.clientHeight;
      document.getElementById('order-list').style.height = orderHeight + 'px';
    },
    created() {
      axios.get('https://www.easy-mock.com/mock/5b8b30dbf032f03c5e71de7f/kuaican/oftenGoods')
        .then(response => {
          this.oftenGoods = response.data;
        }).catch(error => {
        console.log(error);
        alert('网络错误，不能访问');
      });

      //商品分类
      axios.get('https://www.easy-mock.com/mock/5b8b30dbf032f03c5e71de7f/kuaican/typeGoods')
        .then(response => {
          this.type0Goods = response.data[0];
          this.type1Goods = response.data[1];
          this.type2Goods = response.data[2];
          this.type3Goods = response.data[3];

        }).catch(error => {
        console.log(error);
        alert('网络错误，不能访问');
      });

    },
  }
</script>

<style scoped>
  .pos {
    font-size: 12px;
  }

  .pos-order {
    background-color: #f9fafc;
    border-right: 1px solid #c0ccda;
  }

  .order_btn {
    margin-top: 10px;
    text-align: center;
  }

  .title {
    height: 20px;
    border-bottom: 1px solid #d3dce6;
    background-color: #f9fafc;
    padding: 10px;
  }

  .often-goods-list ul li {
    list-style: none;
    float: left;
    border: 1px solid #E5E9F2;
    padding: 10px;
    margin: 5px;
    background-color: #fff;
    cursor: pointer;
  }

  .o-price {
    color: #58b7ff;
  }

  .goods-type {
    clear: both;
  }

  .cookList li {
    list-style: none;
    width: 23%;
    border: 1px solid #e5e9f2;
    height: auto;
    overflow: hidden;
    background-color: #fff;
    padding: 2px;
    float: left;
    margin: 2px;
    cursor: pointer;

  }

  .cookList li span {
    display: block;
    float: left;
  }

  .foodImg {
    width: 40%;
  }

  .foodName {
    font-size: 16px;
    padding-left: 10px;
    color: brown;
  }

  .foodPrice {
    font-size: 16px;
    padding-left: 10px;
    padding-top: 10px;
  }

</style>
