import java.util.*;

class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> list = new ArrayList<>();

        int nums1Index = 0;
        int nums2Index = 0;
        while (nums1Index < nums1.length && nums2Index < nums2.length) {
            int nums1Id = nums1[nums1Index][0];
            int nums1Value = nums1[nums1Index][1];
            int nums2Id = nums2[nums2Index][0];
            int nums2Value = nums2[nums2Index][1];

            if (nums1Id == nums2Id) {
                list.add(new int[]{nums1Id, nums1Value + nums2Value});
                
                nums1Index++;
                nums2Index++;
            } else if (nums1Id < nums2Id) {
                list.add(new int[]{nums1Id, nums1Value});
                
                nums1Index++;
            } else { // nums1Id > nums2Id
                list.add(new int[]{nums2Id, nums2Value});
                
                nums2Index++;
            }
        }

        while (nums1Index < nums1.length) {
            int nums1Id = nums1[nums1Index][0];
            int nums1Value = nums1[nums1Index][1];

            list.add(new int[]{nums1Id, nums1Value});
                
            nums1Index++;
        }
        
        while (nums2Index < nums2.length) {
            int nums2Id = nums2[nums2Index][0];
            int nums2Value = nums2[nums2Index][1];

            list.add(new int[]{nums2Id, nums2Value});
                
            nums2Index++;
        }

        int[][] result = new int[list.size()][2];
        
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
