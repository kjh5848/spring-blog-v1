package shop.mtcoding.blog._core;

public class PagingUtil {

    public static boolean isFirst(int currentPage) {
        return currentPage == 0 ? true : false;
    }

    public static boolean isLast(int currentPage, int totalCount) {
        int totalPageCount = getTotalPageCount(4);
        return currentPage + 1 == totalPageCount ? true : false;
    }


    public static int getTotalPageCount(int totalCount) {

        //1. 나머지 여부 확인
        int remainCount = totalCount % Constant.PAGING_COUNT;
        // 4 % 3 = 1
        System.out.println("remainCount = " + remainCount);
        int totalPageCount = totalCount / Constant.PAGING_COUNT;
        // 4 /3 = 1

        if (remainCount > 0) {
            totalPageCount = totalPageCount + 1;
        }
        System.out.println("totalPageCount = " + totalPageCount);
        return totalPageCount;
    }
}
