/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.common.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * DateUtil
 * TODO
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/4/3
 */
class DateUtilTests {

    @Test
    void testDate() {
        System.out.println(DateUtil.getCurrentYear());
        System.out.println(DateUtil.getCurrentYearMonth());
        System.out.println(DateUtil.getCurrentDate());
        System.out.println(DateUtil.getNextDate());
        System.out.println(DateUtil.getCurrentTime());
        System.out.println(DateUtil.getCurrentDate("yyyy/MM/dd"));
        System.out.println(DateUtil.getCurrentDateTime());
        System.out.println(DateUtil.getCurrentYearMonthShort());
        System.out.println(DateUtil.getCurrentDateShort());
        System.out.println(DateUtil.getNextDateShort());
        System.out.println(DateUtil.getCurrentTimeShort());
        System.out.println(DateUtil.getCurrentDateTimeShort());
        System.out.println(DateUtil.getCurrentDateTime("yyyy/MM/dd HH:mm:ss"));
        System.out.println(DateUtil.getTimestamp());
        System.out.println(DateUtil.intervalDays(LocalDate.of(2020, 5, 7), LocalDate.of(2020, 5, 9)));
        System.out.println(DateUtil.intervalDays(LocalDateTime.now(), LocalDateTime.of(2020, 5, 7, 12, 30, 10)));
        System.out.println(DateUtil.intervalHours(LocalDateTime.of(2020, 5, 7, 12, 30, 10), LocalDateTime.of(2020, 5, 7, 13, 30, 12)));
        System.out.println(DateUtil.intervalMinutes(LocalDateTime.of(2020, 5, 7, 12, 30, 10), LocalDateTime.of(2020, 5, 7, 13, 30, 12)));
        System.out.println(DateUtil.intervalMillis(LocalDateTime.of(2020, 5, 7, 12, 30, 10), LocalDateTime.of(2020, 5, 7, 13, 30, 12)));
        System.out.println(DateUtil.datePlus(LocalDate.now(), ChronoUnit.DAYS, 3));
        System.out.println(DateUtil.datePlus(LocalDate.now(), ChronoUnit.WEEKS, 2));
        System.out.println(DateUtil.dateTimePlus(LocalDateTime.now(), ChronoUnit.MONTHS, 1));
        System.out.println(DateUtil.localDateTimeToLocalDate(LocalDateTime.now()));
        System.out.println(DateUtil.localDateToLocalDateTime(LocalDate.now()));
        System.out.println(DateUtil.localDateToLocalDateTime(LocalDate.now(), 9, 36, 30));
        System.out.println(DateUtil.localDateToLocalDateTime(LocalDate.now(), LocalTime.now()));
        System.out.println(DateUtil.localDateTimeToTimestamp(LocalDateTime.now()));
        System.out.println(DateUtil.formatTimestamp(DateUtil.getTimestamp()));
        System.out.println(DateUtil.formatTimestampShort(DateUtil.getTimestamp()));
        System.out.println(DateUtil.toSelectEpochMilli(LocalDateTime.now(), ZoneOffset.of("+0")));
        System.out.println(DateUtil.datePlus(LocalDate.now(), ChronoUnit.DAYS, 30));
        System.out.println(DateUtil.datePlus(LocalDate.now(), ChronoUnit.DAYS, -30));
        System.out.println(DateUtil.dateTimePlus(LocalDateTime.now(), ChronoUnit.HOURS, 20));
        System.out.println(DateUtil.dateTimePlus(LocalDateTime.now(), ChronoUnit.HOURS, -20));
    }

}
