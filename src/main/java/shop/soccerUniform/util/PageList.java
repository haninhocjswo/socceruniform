package shop.soccerUniform.util;

import java.util.ArrayList;
import java.util.List;

public class PageList {

    public static List<Integer> getPageList(int currentPage, int totalPages) {
        List<Integer> pageList = new ArrayList<>();
        for(int i = currentPage - 1; i < currentPage + 4; i++) {
            if(i < 1) {
                continue;
            }

            if(i > totalPages) {
                break;
            }

            pageList.add(i);
        }

        return pageList;
    }
}
