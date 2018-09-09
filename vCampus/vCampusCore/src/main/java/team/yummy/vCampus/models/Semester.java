package team.yummy.vCampus.models;

import java.time.Year;
import java.util.Calendar;

public class Semester {
    public int yy;
    public int n;

    public Semester() {
        Calendar today = Calendar.getInstance();
        // 由上一年的9月开始，记录月份的偏移量
        int m_offset = today.get(Calendar.MONTH) + 4;
        // 到了第二年9月即偏移1年
        int y_offset = Math.floorDiv(m_offset, 12);
        // yy为最终表示在学期Id上的2位年份数
        yy = today.get(Calendar.YEAR) - 2001 + y_offset;
        // n为当前学期数，分别在偏移1（10月），5（2月）时增1
        n = 1;
        if ((m_offset %= 12) != 0) {
            n += (m_offset < 5 ? 1 : 2);
        }
    }

    public Semester(int yy, int n) {
        this.yy = yy;
        this.n = n;
    }

    public Semester next() {
        return new Semester(yy + (n / 3), (n + 1) % 3);
    }

    public Semester last() {
        return new Semester(yy + (n + 1) / 3 - 1, (n + 1) % 3 + 1);
    }

    public String toString() {
        return String.format("%d-%d-%d", yy, yy + 1, n);
    }
}
