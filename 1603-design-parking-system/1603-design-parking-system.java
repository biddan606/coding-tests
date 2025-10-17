class ParkingSystem {
    private Map<Integer, Integer> spaces;

    public ParkingSystem(int big, int medium, int small) {
        spaces = new HashMap<>();

        spaces.put(1, big);
        spaces.put(2, medium);
        spaces.put(3, small);
    }
    
    public boolean addCar(int carType) {
        int space = spaces.get(carType);
        if (space == 0) {
            return false;
        }

        spaces.put(carType, space - 1);
        return true;
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */