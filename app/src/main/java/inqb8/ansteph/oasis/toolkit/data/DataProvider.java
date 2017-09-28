/******************************************************************************
 *
 *  2016 (C) Copyright Open-RnD Sp. z o.o.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 ******************************************************************************/

package inqb8.ansteph.oasis.toolkit.data;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    /**
     * Do not confuse with MultiLevelListView levels.
     * Following variables refer only to data generation process.
     * For instance, if ITEMS_PER_LEVEL = 2 and MAX_LEVELS = 3,
     * list should look like this:
     *      + 1
     *      | + 1.1
     *      | - - 1.1.1
     *      | - - 1.1.2
     *      | + 1.2
     *      | - - 1.2.1
     *      | - - 1.2.2
     *      | - - 1.2.3
     *      + 2
     *      | + 2.1
     *      | - - 2.1.1
     *      | - - 2.1.2
     *      | + 2.2
     *      | - - 2.2.1
     *      | - - 2.2.2
     */
    private static final int ITEMS_PER_LEVEL = 6;
    private static final int MAX_LEVELS = 2;

    private static final String [] groupHeaders = new String[]{"What should NGOs consider?","What should schools consider?"};

    private static final String[] [] items = new String[][]{
            {
            "Asset Based Community Development",
            "Has your organisation identified the school’s REAL need?",
            "Has your organisation identified the school’s PERCEIVED need?",
            "Does your organisation have a budget for the programme?",
            "Does your organisation have available resources to run a programme?"},

            {
            "Asset Based Community Development",
            "Has your organisation identified the school’s REAL need?",
            "Has your organisation identified the school’s PERCEIVED need?",
            "Does your organisation have a budget for the programme?",
            "Does your organisation have available resources to run a programme?"}


    };



    public static List<BaseItem> getInitialItems() {
        return getSubItems(new GroupItem("root"));
    }

    public static List<BaseItem> getSubItems(BaseItem baseItem) {
        if (!(baseItem instanceof GroupItem)) {
            throw new IllegalArgumentException("GroupItem required");
        }

        GroupItem groupItem = (GroupItem)baseItem;
        if(groupItem.getLevel() >= groupHeaders.length){
            return null;
        }

       // List<BaseItem> result = new ArrayList<>(ITEMS_PER_LEVEL);
        List<BaseItem> result = new ArrayList<>(ITEMS_PER_LEVEL);
        int nextLevel = groupItem.getLevel() + 1;

        int groupNr = 0;
        int itemNr = 0;

        for (int i = 0; i < groupHeaders.length; ++i){
            BaseItem item;
            item = new GroupItem(groupHeaders[i]);
            ((GroupItem)item).setLevel(nextLevel);
            result.add(item);

            for (int g=0; g<5 ; g++)
            {
                item = new Item(items[i][g]);
                result.add(item);
            }


        }

      /*  for (int i = 0; i < ITEMS_PER_LEVEL; ++i) {
            BaseItem item;
            if (i % 2 == 0 && nextLevel != MAX_LEVELS) {
               // item = new GroupItem("Group " + Integer.toString(++groupNr));
                item = new GroupItem(items[i]);
                ((GroupItem)item).setLevel(nextLevel);
            } else {
              //  item = new Item("Item " + Integer.toString(++itemNr));
                item = new Item(items[i]);
            }
            result.add(item);
        }*/
        return result;
    }

    public static boolean isExpandable(BaseItem baseItem) {
        return baseItem instanceof GroupItem;
    }
}
