<template>
  <div class="content">
    <h1>Flexbox布局</h1>
    <h2 class="title">flex-direction 横轴 纵轴排列方式</h2>
    <div class="flex-direction">
      <h3>flex-direction:row</h3>
      <ul id="directionbox1" class="directionbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>flex-direction:row-reverse</h3>
      <ul id="directionbox2" class="directionbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>flex-direction:column</h3>
      <ul id="directionbox3" class="directionbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>flex-direction:column-reverse</h3>
      <ul id="directionbox4" class="directionbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
    </div>


    <h2 class="title">flex-wrap 溢出操作 横轴</h2>
    <div class="flex-wrap">
      <h3>flex-wrap:nowrap</h3>
      <ul id="wrapbox1" class="wrapbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>flex-wrap:wrap</h3>
      <ul id="wrapbox2" class="wrapbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>flex-wrap:wrap-reverse</h3>
      <ul id="wrapbox3" class="wrapbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>

    </div>


    <h2 class="title">flex-flow 纵横轴溢出操作</h2>
    <div class="flex-flow">
      <p>[ flex-direction ]：定义弹性盒子元素的排列方向。</p>
      <p>[ flex-wrap ]：定义弹性盒子元素溢出父容器时是否换行。</p>
      <h3>flex-flow: row nowrap</h3>
      <ul id="flowbox1" class="flowbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>flex-flow: row wrap</h3>
      <ul id="flowbox2" class="flowbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>flex-flow: row wrap-reverse</h3>
      <ul id="flowbox3" class="flowbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>

    </div>


    <h2 class="title">justify-content 横轴排列方式</h2>
    <div class="justify-content">
      <p>justify-content: flex-start | flex-end | center | space-between | space-around</p>
      <h3>justify-content: flex-start</h3>
      <ul id="justifycontentbox1" class="justifycontentbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>justify-content: flex-end</h3>
      <ul id="justifycontentbox2" class="justifycontentbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>justify-content: center</h3>
      <ul id="justifycontentbox3" class="justifycontentbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>justify-content: space-between</h3>
      <ul id="justifycontentbox4" class="justifycontentbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>justify-content: space-around</h3>
      <ul id="justifycontentbox5" class="justifycontentbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>

    </div>

    <h2 class="title">align-items 纵轴排列方式</h2>
    <div class="align-items">
      <p>align-items: flex-start | flex-end | center | baseline | stretch</p>
      <h3>align-items: flex-start</h3>
      <ul id="alignitemsbox1" class="alignitemsbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>align-items: flex-end</h3>
      <ul id="alignitemsbox2" class="alignitemsbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>align-items: center</h3>
      <ul id="alignitemsbox3" class="alignitemsbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>align-items: baseline</h3>
      <ul id="alignitemsbox4" class="alignitemsbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
      <h3>align-items: stretch</h3>
      <ul id="alignitemsbox5" class="alignitemsbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>

    </div>


    <h2 class="title">align-content</h2>
    <p>设置或检索弹性盒堆叠伸缩行的对齐方式。</p>
    <div class="align-items">
      <p>align-content: flex-start | flex-end | center | space-between | space-around | stretch</p>
      <h3>align-content: flex-start</h3>
      <ul id="aligncontentbox1" class="aligncontentbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
        <li>d</li>
        <li>e</li>
        <li>f</li>
      </ul>
      <h3>align-content: flex-end</h3>
      <ul id="aligncontentbox2" class="aligncontentbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
        <li>d</li>
        <li>e</li>
        <li>f</li>
      </ul>
      <h3>align-content: center</h3>
      <ul id="aligncontentbox3" class="aligncontentbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
        <li>d</li>
        <li>e</li>
        <li>f</li>
      </ul>
      <h3>align-content: space-between</h3>
      <ul id="aligncontentbox4" class="aligncontentbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
        <li>d</li>
        <li>e</li>
        <li>f</li>
      </ul>
      <h3>align-content: space-around</h3>
      <ul id="aligncontentbox5" class="aligncontentbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
        <li>d</li>
        <li>e</li>
        <li>f</li>
      </ul>
      <h3>align-content: stretch</h3>
      <ul id="aligncontentbox6" class="aligncontentbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
        <li>d</li>
        <li>e</li>
        <li>f</li>
      </ul>
    </div>


    <h2 class="title">order</h2>
    <p>适用于弹性盒模型容器子元素</p>
    <div class="order">
      <p> order: integer：用整数值来定义排列顺序，数值小的排在前面。可以为负值。</p>
      <ul id="orderbox1" class="orderbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
        <li>d</li>
        <li>e</li>
        <li>f</li>
      </ul>
    </div>


    <h2 class="title">flex-grow</h2>
    <p>适用于弹性盒模型容器子元素</p>
    <div class="flex-grow">
      <p> flex-grow: number。用数值来定义扩展比率。不允许负值 </p>
      <p>flex-grow的默认值为0，如果没有显示定义该属性，是不会拥有分配剩余空间权利的。本例中b,c两项都显式的定义了flex-grow，可以看到总共将剩余空间分成了4份，其中b占1份，c占3分，即1:3</p>
      <ul id="flexgrowbox" class="flexgrowbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
        <li>d</li>
        <li>e</li>
        <li>f</li>
      </ul>
    </div>


    <h2 class="title">flex-shrink</h2>
    <p>适用于弹性盒模型容器子元素</p>
    <div class="flex-shrink">
      <p> flex-shrink: number (default 1)。flex-shrink的默认值为1，如果没有显示定义该属性，将会自动按照默认值1在所有因子相加之后计算比率来进行空间收缩。 </p>
      <ul id="flexshrinkbox" class="flexshrinkbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
      </ul>
    </div>


    <h2 class="title">flex-basis</h2>
    <p>适用于弹性盒模型容器子元素</p>
    <div class="flex-basis">
      <p> flex-basis: length | auto (default auto) </p>
      <p>auto：无特定宽度值，取决于其它属性值</p>
      <p>length：用长度值来定义宽度。不允许负值</p>
      <p>percentage：用百分比来定义宽度。不允许负值</p>
      <ul id="flexbasisbox" class="flexbasisbox">
        <li>a</li>
        <li>b</li>
        <li>c</li>
        <li>d</li>
        <li>e</li>
      </ul>
    </div>


    <h2 class="title">flex</h2>
    <p>适用于弹性盒模型子元素</p>
    <div class="flex">
      <p> flex：none | [ flex-grow ] || [ flex-shrink ] || [ flex-basis ] </p>
      <p> none：none关键字的计算值为: 0 0 auto</p>
      <p>[ flex-grow ]：定义弹性盒子元素的扩展比率。</p>
      <p>[ flex-grow ]：定义弹性盒子元素的扩展比率。</p>
      <p>[ flex-grow ]：定义弹性盒子元素的扩展比率。</p>
      <p> [ flex-basis ]：定义弹性盒子元素的默认基准值。</p>
      <p>下例中，定义了父容器宽（即主轴宽）为800px，由于子元素设置了伸缩基准值flex-basis，相加300+500+600=1400，那么子元素将会溢出1400-800=600px；
        由于同时设置了收缩因子，所以加权综合可得300*1+500*2+600*3=3100px；
        于是我们可以计算a,b,c将被移除的溢出量是多少：
        a被移除溢出量：300*1/3100*600=3/31，即约等于58px
        b被移除溢出量：500*2/3100*600=10/31，即约等于194px
        c被移除溢出量：600*3/3100*600=18/31，即约等于348px
        最后a,b,c的实际宽度分别为：300-58=242px, 500-194=306px, 600-348=252px</p>
      <ul id="flexbox1" class="flexbox">
        <li>flex:1;</li>
        <li>flex:1;</li>
        <li>flex:1;</li>
      </ul>
      <ul id="flexbox2" class="flexbox">
        <li>flex:1 0 100px;</li>
        <li>flex:2 0 100px;</li>
        <li>flex:3 0 100px;</li>
      </ul>
      <ul id="flexbox3" class="flexbox">
        <li>flex:1 1 300px;</li>
        <li>flex:1 2 500px;</li>
        <li>flex:1 3 600px;</li>
      </ul>
    </div>

    <h2 class="title">align-self</h2>
    <p>适用于弹性盒模型子元素</p>
    <p>align-self: auto | flex-start | flex-end | center | baseline | stretch</p>
    <p>auto：如果'align-self'的值为'auto'，则其计算值为元素的父元素的'align-items'值，如果其没有父元素，则计算值为'stretch'。<br>
      flex-start：弹性盒子元素的侧轴（纵轴）起始位置的边界紧靠住该行的侧轴起始边界。<br>
      flex-end：弹性盒子元素的侧轴（纵轴）起始位置的边界紧靠住该行的侧轴结束边界。<br>
      center：弹性盒子元素在该行的侧轴（纵轴）上居中放置。（如果该行的尺寸小于弹性盒子元素的尺寸，则会向两个方向溢出相同的长度）。<br>
      baseline：如弹性盒子元素的行内轴与侧轴为同一条，则该值与'flex-start'等效。其它情况下，该值将参与基线对齐。<br>
      stretch：如果指定侧轴大小的属性值为'auto'，则其值会使项目的边距盒的尺寸尽可能接近所在行的尺寸，但同时会遵照'min/max-width/height'属性的限制。<br></p>
    <div class="align-self">

      <ul id="alignselfbox" class="alignselfbox">
        <li>start</li>
        <li>end</li>
        <li>center</li>
        <li>baseline</li>
        <li>stretch</li>
        <li>auto</li>
        <li>baseline</li>
        <li>none</li>
        <li>none</li>
      </ul>

    </div>

  </div>
</template>

<script>
  export default {
    name: "FlexBox"
  };
</script>

<style scoped>
  .content{
    padding-left: 20px;
  }
  .directionbox {
    display: -webkit-flex;
    display: flex;
    margin: 0px;
    padding: 10px;
    list-style: none;
    background-color: #eee;
  }

  .directionbox li {
    width: 100px;
    height: 100px;
    border: 1px solid #aaa;
    text-align: center;
  }

  #directionbox1 {
    flex-direction: row;
    -webkit-flex-direction: row;
  }

  #directionbox2 {
    flex-direction: row-reverse;
    -webkit-flex-direction: row-reverse;
  }

  #directionbox3 {
    flex-direction: column;
    -webkit-flex-direction: column;
  }

  #directionbox4 {
    flex-direction: column-reverse;
    -webkit-flex-direction: column-reverse;
  }

  .title {
    color: red;
  }

  /*wrapbox*/

  .wrapbox {
    display: -webkit-flex;
    display: flex;
    margin: 0px;
    padding: 10px;
    list-style: none;
    background-color: #eee;
  }

  .wrapbox li {
    width: 400px;
    height: 100px;
    border: 1px solid #aaa;
    text-align: center;
  }

  #wrapbox1 {
    flex-wrap: nowrap;
    -webkit-flex-wrap: nowrap;
  }

  #wrapbox2 {
    flex-wrap: wrap;
    -webkit-flex-wrap: wrap;
  }

  #wrapbox3 {
    flex-wrap: wrap-reverse;
    -webkit-flex-wrap: wrap-reverse;
  }

  /* flex-flow: <‘flex-direction’> || <‘flex-wrap’> */

  .flowbox {
    display: -webkit-flex;
    display: flex;
    margin: 0px;
    padding: 10px;
    list-style: none;
    background-color: #eee;
  }

  .flowbox li {
    width: 400px;
    height: 100px;
    border: 1px solid #aaa;
    text-align: center;
  }

  #flowbox1 {
    flex-flow: row nowrap;
    -webkit-flex-flow: row nowrap;
  }

  #flowbox2 {
    flex-flow: row wrap;
    -webkit-flex-flow: row wrap;
  }

  #flowbox3 {
    flex-flow: row wrap-reverse;
    -webkit-flex-flow: row wrap-reverse;
  }

  /*justifycontentbox*/

  .justifycontentbox {
    display: -webkit-flex;
    display: flex;
    margin: 0px;
    padding: 10px;
    list-style: none;
    background-color: #eee;
  }

  .justifycontentbox li {
    width: 100px;
    height: 100px;
    border: 1px solid #aaa;
    text-align: center;
  }

  #justifycontentbox1 {
    justify-content: flex-start;
    -webkit-justify-content: flex-start;
  }

  #justifycontentbox2 {
    justify-content: flex-end;
    -webkit-justify-content: flex-end;
  }

  #justifycontentbox3 {
    justify-content: center;
    -webkit-justify-content: center;
  }

  #justifycontentbox4 {
    justify-content: space-between;
    -webkit-justify-content: space-between;
  }

  #justifycontentbox5 {
    justify-content: space-around;
    -webkit-justify-content: space-around;
  }

  /*alignitemsbox*/

  .alignitemsbox {
    display: -webkit-flex;
    display: flex;
    margin: 0px;
    padding: 10px;
    list-style: none;
    background-color: #eee;
    height: 200px;

  }

  .alignitemsbox li {
    width: 100px;
    height: 50px;
    margin: 5px;
    border: 1px solid #aaa;
    text-align: center;
    background-color: antiquewhite;
  }

  .alignitemsbox li:nth-child(1) {
    padding: 10px;
  }

  .alignitemsbox li:nth-child(2) {
    padding: 15px 10px;
  }

  .alignitemsbox li:nth-child(3) {
    padding: 20px 10px;
  }

  #alignitemsbox1 {
    align-items: flex-start;
    -webkit-align-items: flex-start;
  }

  #alignitemsbox2 {
    align-items: flex-end;
    -webkit-align-items: flex-end;
  }

  #alignitemsbox3 {
    align-items: center;
    -webkit-align-items: center;
  }

  #alignitemsbox4 {
    align-items: baseline;
    -webkit-align-items: baseline;
  }

  #alignitemsbox4 {
    align-items: stretch;
    -webkit-align-items: stretch;
  }

  /*aligncontentbox*/

  .aligncontentbox {
    display: -webkit-flex;
    display: flex;
    -webkit-flex-wrap: wrap;
    margin: 0px;
    padding: 0px;
    list-style: none;
    background-color: #eee;
    height: 200px;
    width: 200px;

  }

  .aligncontentbox li {
    padding: 10px;
    margin: 5px;
    border: 1px solid #aaa;
    text-align: center;
    background-color: antiquewhite;
  }

  #aligncontentbox1 {
    align-content: flex-start;
    -webkit-align-content: flex-start;
  }

  #aligncontentbox2 {
    align-content: flex-end;
    -webkit-align-content: flex-end;
  }

  #aligncontentbox3 {
    align-content: center;
    -webkit-align-content: center;
  }

  #aligncontentbox4 {
    align-content: space-between;
    -webkit-align-content: space-between;
  }

  #aligncontentbox5 {
    align-content: space-around;
    -webkit-align-content: space-around;
  }

  #aligncontentbox6 {
    align-content: stretch;
    -webkit-align-content: stretch;
  }

  /*orderbox*/

  .orderbox {
    display: -webkit-flex;
    display: flex;
    margin: 0px;
    padding: 10px;
    list-style: none;
    background-color: #eee;
  }

  .orderbox li {
    width: 100px;
    line-height: 100px;
    border: 1px solid #aaa;
    text-align: center;
    background-color: antiquewhite;
  }

  #orderbox1 li:nth-child(3) {
    order: -1;
    -webkit-order: -1;

  }

  /*flexgrowbox*/

  .flexgrowbox {
    display: -webkit-flex;
    display: flex;
    width: 600px;
    margin: 0;
    padding: 10px;
    list-style: none;
    background-color: #eee;
  }

  .flexgrowbox li {
    width: 100px;
    height: 100px;
    border: 1px solid #aaa;
    text-align: center;
  }

  #flexgrowbox li:nth-child(2) {
    -webkit-flex-grow: 1;
    flex-grow: 1;
  }

  #flexgrowbox li:nth-child(3) {
    -webkit-flex-grow: 2;
    flex-grow: 2;
  }

  /*flexshrinkbox*/

  #flexshrinkbox {
    display: -webkit-flex;
    display: flex;
    width: 400px;
    margin: 0;
    padding: 0;
    list-style: none;
  }

  #flexshrinkbox li {
    width: 200px;
  }

  #flexshrinkbox li:nth-child(1) {
    background: #888;
  }

  #flexshrinkbox li:nth-child(2) {
    background: #ccc;
  }

  #flexshrinkbox li:nth-child(3) {
    -webkit-flex-shrink: 3;
    flex-shrink: 3;
    background: #aaa;
  }

  /*flexbasisbox*/
  .flexbasisbox {
    display: -webkit-flex;
    display: flex;
    width: 600px;
    margin: 0;
    padding: 10px;
    list-style: none;
    background-color: #eee;
  }

  .flexbasisbox li {
    width: 100px;
    height: 100px;
    border: 1px solid #aaa;
    text-align: center;
  }

  #flexbasisbox li:nth-child(3) {
    -webkit-flex-basis: 600px;
    flex-basis: 600px;
  }

  /*flexbox*/
  .flexbox {
    display: -webkit-flex;
    display: flex;
    max-width: 400px;
    height: 100px;
    margin: 10px 0 0;
    padding: 0;
    border-radius: 5px;
    list-style: none;
    background-color: #eee;
  }

  .flexbox li {
    background-color: #aaa;
    text-align: center;
  }

  .flexbox li:nth-child(1) {
    background-color: #999;
  }

  .flexbox li:nth-child(2) {
    background-color: #aaa;
  }

  .flexbox li:nth-child(3) {
    background-color: #ccc;
  }

  #flexbox1 li:nth-child(1) {
    -webkit-flex: 1;
    flex: 1;
  }

  #flexbox1 li:nth-child(2) {
    -webkit-flex: 1;
    flex: 1;
  }

  #flexbox1 li:nth-child(3) {
    -webkit-flex: 1;
    flex: 1;
  }

  #flexbox2 li:nth-child(1) {
    -webkit-flex: 1 0 100px;
    flex: 1 0 100px;
  }

  #flexbox2 li:nth-child(2) {
    -webkit-flex: 2 0 100px;
    flex: 2 0 100px;
  }

  #flexbox2 li:nth-child(3) {
    -webkit-flex: 3 0 100px;
    flex: 3 0 100px;
  }

  #flexbox3 {
    max-width: 800px;
  }

  #flexbox3 li:nth-child(1) {
    -webkit-flex: 1 1 300px;
    flex: 1 1 300px;
  }

  #flexbox3 li:nth-child(2) {
    -webkit-flex: 1 2 500px;
    flex: 1 2 500px;
  }

  #flexbox3 li:nth-child(3) {
    -webkit-flex: 1 3 600px;
    flex: 1 3 600px;
  }

  /*alignselfbox*/

  .alignselfbox {
    display: -webkit-flex;;
    display: flex;
    -webkit-align-items: flex-end;
    height: 200px;
    margin: 0;
    padding: 10px;
    border-radius: 5px;
    list-style: none;
    background-color: #eee;
  }

  .alignselfbox li {
    margin: 5px;
    padding: 10px;
    border-radius: 5px;
    background-color: #aaa;
    text-align: center;
  }

  #alignselfbox li:nth-child(1) {
    -webkit-align-self: flex-start;
    align-self: flex-start;
  }

  #alignselfbox li:nth-child(2) {
    -webkit-align-self: flex-end;
    align-self: flex-end;
  }

  #alignselfbox li:nth-child(3) {
    -webkit-align-self: center;
    align-self: center;
  }

  #alignselfbox li:nth-child(4) {
    -webkit-align-self: baseline;
    align-self: baseline;
  }

  #alignselfbox li:nth-child(5) {
    -webkit-align-self: stretch;
    align-self: stretch;
  }

  #alignselfbox li:nth-child(6) {
    -webkit-align-self: auto;
    align-self: auto;
  }

  #alignselfbox li:nth-child(7) {
    -webkit-align-self: baseline;
    align-self: baseline;
  }


</style>
