package com.ticy.manage.utils;


import org.springframework.util.StringUtils;

/**
 * @Author tkk
 * @Time 2021/4/20 10:53
 * @Description todo
 */
public class CronUtils {

    /**
     * CRON表达式翻译为中文描述
     * 复杂表达式和部分通配符不支持
     *
     * @param cronStr cron表达式
     * @return 中文描述
     */
    public static String translateToChinese(String cronStr) {
        if (cronStr==null || "".equals(cronStr) ) {
            throw new IllegalArgumentException("cron表达式为空");
        }

        String[] cronArray = cronStr.split(" ");
        // 表达式到年会有7段， 至少6段
        if (cronArray.length != 6 && cronArray.length != 7) {
            throw new IllegalArgumentException("cron表达式格式错误");
        }
        StringBuilder result = new StringBuilder();
        String secondCron = cronArray[0];
        String minuteCron = cronArray[1];
        String hourCron = cronArray[2];
        String dayCron = cronArray[3];
        String monthCron = cronArray[4];
        String weekCron = cronArray[5];
        if(cronArray.length>6){

            String yearCron = cronArray[6];
            // 解析月
            if (!yearCron.equals("*") && !yearCron.equals("?")) {
               /* if (yearCron.contains("/")) {
                    result.append("从")
                            .append(yearCron.split("/")[0])
                            .append("年开始")
                            .append(",每")
                            .append(yearCron.split("/")[1])
                            .append("年");
                } else {*/
                    //  result.append("每年").append(monthCron).append("月");
                    result.append(yearCron).append("年");
               // }
            }else {
                if ( yearCron.equals("*")){
                    result.append("每年");
                }

            }
        }

        if (!monthCron.equals("*") && !monthCron.equals("?")) {
            if (monthCron.contains("/")) {
                result.append("从")
                        .append(monthCron.split("/")[0])
                        .append("号开始")
                        .append(",每")
                        .append(monthCron.split("/")[1])
                        .append("月");
            } else {
                //  result.append("每年").append(monthCron).append("月");
                result.append(monthCron).append("月");
            }
        }else {
            if ( monthCron.equals("*")){
                result.append("每月");
            }

        }

        // 解析周
        boolean hasWeekCron = false;
        if (!weekCron.equals("*") && !weekCron.equals("?")) {
            hasWeekCron = true;
            result.append("每周");
            String[] tmpArray = weekCron.split(",");
            for (String tmp : tmpArray) {
                switch (tmp) {
                    case "SUN":
                        result.append("日");
                        continue;
                    case "MON":
                        result.append("一");
                        continue;
                    case "TUE":
                        result.append("二");
                        continue;
                    case "WED":
                        result.append("三");
                        continue;
                    case "THU":
                        result.append("四");
                        continue;
                    case "FRI":
                        result.append("五");
                        continue;
                    case "SAT":
                        result.append("六");
                        continue;
                    //default:
                       // result.append(tmp);
                       // continue;
                }
            }
        }
        if ( weekCron.equals("*")){
            result.append("每周");
        }
        if (weekCron.contains("-")){
            //result.append("周")
            String[] split = weekCron.split("-");
            result.append(split[0])
                    .append("至周")
                    .append(split[1]);
        }
        //   }

        // 解析日
        if (!dayCron.equals("?") && !"*".equals(dayCron)) {
            if (hasWeekCron) {
                throw new IllegalArgumentException("表达式错误，不允许同时存在指定日和指定星期");
            }
            if (dayCron.contains("-")) {
                result.append("每月从第")
                        .append(dayCron.split("-")[0])
                        .append("天开始")
                        .append(",每")
                        .append(dayCron.split("-")[1])
                        .append("天");
            } else {
                result.append("每月第").append(dayCron).append("天");
            }
        }else {
            if ( dayCron.equals("*")){
                result.append("每日");
            }

        }

        // 解析时
        if (!hourCron.equals("*")) {
            if (hourCron.contains("/")) {
                result.append("从")
                        .append(hourCron.split("/")[0])
                        .append("点开始")
                        .append(",每")
                        .append(hourCron.split("/")[1])
                        .append("小时");
            } else {
                if ((result.toString().length() > 0)) {
                    result.append("每天").append(hourCron).append("点");
                }
            }
        }
        else {

            result.append("每小时");

        }

        // 解析分
        if (!minuteCron.equals("*") && !minuteCron.equals("0")) {
            if (minuteCron.contains("/")) {
                result.append("从第")
                        .append(minuteCron.split("/")[0])
                        .append("分开始").append(",每")
                        .append(minuteCron.split("/")[1])
                        .append("分");
            } else {
                result.append(minuteCron).append("分");
            }
        }else {
            if ( minuteCron.equals("*")){
                result.append("每分");
            }

        }

        // 解析秒
        if (!secondCron.equals("*") && !secondCron.equals("0")) {
            if (secondCron.contains("/")) {
                result.append("从第")
                        .append(secondCron.split("/")[0])
                        .append("秒开始")
                        .append(",每")
                        .append(secondCron.split("/")[1])
                        .append("秒");
            } else {
                result.append(secondCron).append("秒");
            }
        }else {
            if ( secondCron.equals("*")){
                result.append("每秒");
            }

        }

        if (result!=null && !"".equals(result.toString())) {
            result.append("执行一次");
        }
        return result.toString();
    }


    public static void main(String[] args) {
        String cron = "1 0 1 ? 01,10,03,04,09 周一,周五,周四,周六 2021-2023";
        String cron2 = "0 0 0 ? * 1-2 *";
        String cron3 = "0 0 1 1-8 1-4 ? *";
        String cron4 = "0 0 0 ? 2/3 * ?";
        String s = translateToChinese("0 38 19 * * ? *");
        System.out.println(s);
    }
}
