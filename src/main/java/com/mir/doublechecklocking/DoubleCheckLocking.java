package com.mir.doublechecklocking;

/**
 * Created by Mir on 08/10/2016.
 */
public class DoubleCheckLocking {

    public static class SearchBox {
        private static volatile SearchBox searchBox;

        // private attribute of this class
        private String searchWord = "";
        private String[] list = new String[]{"Stack", "Overflow"};

        // private constructor
        private SearchBox() {}

        // static method to get instance
        public static SearchBox getInstance() {
            if (searchBox == null) { // first time lock
                synchronized (SearchBox.class) {
                    if (searchBox == null) {  // second time lock
                        searchBox = new SearchBox();
                    }
                }
            }
            return searchBox;
        }
    }
}
