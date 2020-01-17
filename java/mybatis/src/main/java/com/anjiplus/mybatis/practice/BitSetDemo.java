package com.anjiplus.mybatis.practice;

import java.util.BitSet;
import java.util.Set;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/22 10:51
 * @Description: BitSet
 */
public class BitSetDemo {
    
    private static void bitsetTest(){
        BitSet bitSet1 = new BitSet(16);
        BitSet bitSet2 = new BitSet(16);
        for (int i = 0; i < 16; i++) {
            if (i%2 == 0){
                bitSet1.set(i);
            }
            
            if (i%5 != 0){
                bitSet2.set(i);
            }
        }
        System.out.println("Initial pattern in bits1: ");
        System.out.println(bitSet1);
        System.out.println("Initial pattern in bits2: ");
        System.out.println(bitSet2);
        //AND bits 对此目标位 set 和参数位 set 执行逻辑与操作。
//        bitSet2.and(bitSet1);
//        System.out.println("逻辑与操作 bits2 AND bits1: \n" + bitSet2);

//        bitSet2.or(bitSet1);
//        System.out.println("逻辑或操作 bits2 or bits1: \n" + bitSet2);

        bitSet2.xor(bitSet1);
        System.out.println("逻辑非操作 bits2 xor bits1: \n" + bitSet2);

    }

    public static void main(String[] args) {
        bitsetTest();
    }
}
