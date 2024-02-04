package shop.mtcoding.blog._core;

public class PagingUtil {

    public static boolean isFirst(int currentPage) {

        return currentPage == 0 ? true : false;

    }

    public static boolean isLast(int currentPage, int totalCount) {
        int totalPageCount = getTotalPageCount(totalCount);
        System.out.println("currentPage = " + currentPage);
        return currentPage+1 == totalPageCount ? true : false;

    }

    public static int getTotalPageCount(int totalCount) {
        System.out.println("totalCount = " + totalCount);
        int remainCount = totalCount % Constant.PAGING_COUNT;
        System.out.println("remainCount = " + remainCount);

        int totalPageCount = totalCount / Constant.PAGING_COUNT;

        if (remainCount > 0) {
            totalPageCount = totalPageCount + 1;
        }
        System.out.println("totalPageCount = " + totalPageCount);

        return totalPageCount;
    }

}
