class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, (a, b) -> a.length() - b.length());
        Set<String> root = new HashSet<>();

        for (String fullPath : folder) {
            String[] seperatedPath = Arrays.stream(fullPath.split("/"))
                .filter(s -> !s.isEmpty())
                .map(s -> "/" + s)
                .toArray(String[]::new);

            String currentPath = "";
            boolean added = false;
            for (String path : seperatedPath) {
                currentPath += path;

                if (root.contains(currentPath)) {
                    added = true;
                    break;
                }
            }

            if (!added) {
                root.add(currentPath);
            }
        }

        return new ArrayList<>(root);
    }
}
