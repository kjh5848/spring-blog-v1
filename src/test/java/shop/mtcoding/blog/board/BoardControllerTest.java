package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BoardControllerTest {
    @Test
    public void count() {
        int totalCount = 10;
        int pagingCount = 3;
        int remainCount = totalCount % pagingCount;
        //3 % 3 = 0
        //5 % 3 = 1
        System.out.println("remainCount = "+remainCount);

        int totalPageCount = totalCount / pagingCount;
        // 3 / 3 = 1
        // 5 / 3 = 1

        if(remainCount >0)

        {
            totalPageCount = totalPageCount + 1;
        }
        System.out.println("totalPageCount = "+totalPageCount);
    }
}