package com.smart_system_academy.utils.date;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtil {
  public static LocalDateTime getTimeZoneNow() {
    return LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
  }
}
