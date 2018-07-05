package com.sfu.cmpt470.pojo;

public class DishVal {
    private long _dishID;
    private String _dishName;
    private String _description;
    private float _price;
    private DishVerVal _verVal;

    private DishVal() {

    }

    private DishVal(long dishID, DishVerVal dishVerVal, String dishName, String description, float price) {
        _dishID = dishID;
        _dishName = dishName;
        _description = description;
        _price = price;
        _verVal = dishVerVal;
    }

    public long getDishID() {
        return _dishID;
    }

    public String getDishName() {
        return _dishName;
    }

    public String getDescription() {
        return _description;
    }

    public float getPrice() {
        return _price;
    }

    public DishVerVal getVerVal(){
        return _verVal;
    }
    public DishBuilder toBuilder() {
        DishBuilder dishBuilder = newBuilder();
        dishBuilder.setDishID(this.getDishID())
                .setDishName(this.getDishName())
                .setDescription(this.getDescription())
                .setDescription(this.getDescription())
                .setPrice(this.getPrice());
        return dishBuilder;
    }

    public static DishBuilder newBuilder() {
        return new DishBuilder();
    }

    public static class DishBuilder {
        private long _dishID;
        private String _dishName;
        private String _description;
        private float _price;
        private DishVerVal _verVal;

        public DishBuilder() {
            _dishID = -1;
            _price = -1;
        }

        public DishBuilder setDishID(long dishID) {
            _dishID = dishID;
            return this;
        }

        public DishBuilder setDishName(String dishName) {
            _dishName = dishName;
            return this;
        }

        public DishBuilder setDescription(String description) {
            _description = description;
            return this;
        }

        public DishBuilder setPrice(float price) {
            _price = price;
            return this;
        }

        public void setVerVal(DishVerVal verVal) {
            _verVal = verVal;
        }

        public DishVal build() {
            return new DishVal(_dishID, _verVal, _dishName, _description, _price);
        }
    }
}
