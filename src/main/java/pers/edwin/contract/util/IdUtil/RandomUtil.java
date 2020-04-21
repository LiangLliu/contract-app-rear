package pers.edwin.contract.util.IdUtil;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author Jinzi Yuan
 * Create Data: 2020/4/6 17:00
 * @Description :
 */
public class RandomUtil {

    private static final ThreadLocalRandom RANDOM =ThreadLocalRandom.current();
    //生成订单编号-方式一
    public static String generateId(){
        return Instant.now().toEpochMilli() + generateNumber(4);
    }

    //N为随机数流水号
    public static String generateNumber(final int num){
        StringBuilder sb=new StringBuilder();
        for (int i=1;i<=num;i++){
            sb.append(RANDOM.nextInt(9));
        }
        return sb.toString();
    }
}