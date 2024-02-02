package shop.mtcoding.blog;

import org.junit.jupiter.api.Test;

public class PagingTest {

    @Test
    public void count() {

        int totalCount = 10;
        int pagingCount = 3;

        //1. 나머지 여부 확인
        int remainCount = totalCount % pagingCount;
        // 4 % 3 = 1
        System.out.println("remainCount = " + remainCount);
        int totalPageCount = totalCount / pagingCount;

        if (remainCount > 0) {
            totalPageCount = totalPageCount + 1;
        }
        System.out.println("totalPageCount = " + totalPageCount);
    }
}
