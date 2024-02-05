package shop.mtcoding.blog._core;

public class PagingUtil {

    public static boolean isFirst(int currentPage) {
        return currentPage == 0 ? true : false;
    }

    public static boolean isLast(int currentPage, int totalcount) {
        int totalPageCount = getTotalPage(totalcount);
        return currentPage + 1 == totalPageCount ? true : false;
    }

    public static int getTotalPage(int totalCount) {



        int pagingCount = 3;
        int remainCount = totalCount % pagingCount;
        int totalPageCount = totalCount / pagingCount;

        if (remainCount > 0) {
            totalPageCount += 1;
        }
        return totalPageCount;
    }
}
