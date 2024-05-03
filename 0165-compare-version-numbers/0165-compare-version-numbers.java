class Solution {
    public int compareVersion(String version1, String version2) {
        List<Integer> detailedVersion1 = toDetailedVersion(version1);
        List<Integer> detailedVersion2 = toDetailedVersion(version2);

        return compare(detailedVersion1, detailedVersion2);
    }

    private List<Integer> toDetailedVersion(String version) {
        String[] tokens = version.split("\\.");
        
        List<Integer> detailedVersion = new ArrayList<>();
        for (String s : tokens) {
            detailedVersion.add(toVersion(s));
        }
        return detailedVersion;
    }

    private Integer toVersion(String versionAsString) {
        int version = 0;
        for (char c : versionAsString.toCharArray()) {
            version = (version * 10) + (c - '0');
        }
        return version;
    }

    private int compare(List<Integer> version1, List<Integer> version2) {
        for (int i = 0; i < Math.max(version1.size(), version2.size()); i++) {
            int n1 = version1.size() > i ? version1.get(i) : 0;
            int n2 = version2.size() > i ? version2.get(i) : 0;

            if (n1 > n2) {
                return 1;
            } else if (n1 < n2) {
                return -1;
            }
        }
        
        return 0;
    }
}