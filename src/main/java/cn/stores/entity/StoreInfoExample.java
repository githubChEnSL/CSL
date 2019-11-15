package cn.stores.entity;

import java.util.ArrayList;
import java.util.List;

public class StoreInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StoreInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andStoreidIsNull() {
            addCriterion("storeid is null");
            return (Criteria) this;
        }

        public Criteria andStoreidIsNotNull() {
            addCriterion("storeid is not null");
            return (Criteria) this;
        }

        public Criteria andStoreidEqualTo(String value) {
            addCriterion("storeid =", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotEqualTo(String value) {
            addCriterion("storeid <>", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThan(String value) {
            addCriterion("storeid >", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThanOrEqualTo(String value) {
            addCriterion("storeid >=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThan(String value) {
            addCriterion("storeid <", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThanOrEqualTo(String value) {
            addCriterion("storeid <=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLike(String value) {
            addCriterion("storeid like", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotLike(String value) {
            addCriterion("storeid not like", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidIn(List<String> values) {
            addCriterion("storeid in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotIn(List<String> values) {
            addCriterion("storeid not in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidBetween(String value1, String value2) {
            addCriterion("storeid between", value1, value2, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotBetween(String value1, String value2) {
            addCriterion("storeid not between", value1, value2, "storeid");
            return (Criteria) this;
        }

        public Criteria andShorenameIsNull() {
            addCriterion("shorename is null");
            return (Criteria) this;
        }

        public Criteria andShorenameIsNotNull() {
            addCriterion("shorename is not null");
            return (Criteria) this;
        }

        public Criteria andShorenameEqualTo(String value) {
            addCriterion("shorename =", value, "shorename");
            return (Criteria) this;
        }

        public Criteria andShorenameNotEqualTo(String value) {
            addCriterion("shorename <>", value, "shorename");
            return (Criteria) this;
        }

        public Criteria andShorenameGreaterThan(String value) {
            addCriterion("shorename >", value, "shorename");
            return (Criteria) this;
        }

        public Criteria andShorenameGreaterThanOrEqualTo(String value) {
            addCriterion("shorename >=", value, "shorename");
            return (Criteria) this;
        }

        public Criteria andShorenameLessThan(String value) {
            addCriterion("shorename <", value, "shorename");
            return (Criteria) this;
        }

        public Criteria andShorenameLessThanOrEqualTo(String value) {
            addCriterion("shorename <=", value, "shorename");
            return (Criteria) this;
        }

        public Criteria andShorenameLike(String value) {
            addCriterion("shorename like", value, "shorename");
            return (Criteria) this;
        }

        public Criteria andShorenameNotLike(String value) {
            addCriterion("shorename not like", value, "shorename");
            return (Criteria) this;
        }

        public Criteria andShorenameIn(List<String> values) {
            addCriterion("shorename in", values, "shorename");
            return (Criteria) this;
        }

        public Criteria andShorenameNotIn(List<String> values) {
            addCriterion("shorename not in", values, "shorename");
            return (Criteria) this;
        }

        public Criteria andShorenameBetween(String value1, String value2) {
            addCriterion("shorename between", value1, value2, "shorename");
            return (Criteria) this;
        }

        public Criteria andShorenameNotBetween(String value1, String value2) {
            addCriterion("shorename not between", value1, value2, "shorename");
            return (Criteria) this;
        }

        public Criteria andRegulatoridIsNull() {
            addCriterion("regulatorid is null");
            return (Criteria) this;
        }

        public Criteria andRegulatoridIsNotNull() {
            addCriterion("regulatorid is not null");
            return (Criteria) this;
        }

        public Criteria andRegulatoridEqualTo(String value) {
            addCriterion("regulatorid =", value, "regulatorid");
            return (Criteria) this;
        }

        public Criteria andRegulatoridNotEqualTo(String value) {
            addCriterion("regulatorid <>", value, "regulatorid");
            return (Criteria) this;
        }

        public Criteria andRegulatoridGreaterThan(String value) {
            addCriterion("regulatorid >", value, "regulatorid");
            return (Criteria) this;
        }

        public Criteria andRegulatoridGreaterThanOrEqualTo(String value) {
            addCriterion("regulatorid >=", value, "regulatorid");
            return (Criteria) this;
        }

        public Criteria andRegulatoridLessThan(String value) {
            addCriterion("regulatorid <", value, "regulatorid");
            return (Criteria) this;
        }

        public Criteria andRegulatoridLessThanOrEqualTo(String value) {
            addCriterion("regulatorid <=", value, "regulatorid");
            return (Criteria) this;
        }

        public Criteria andRegulatoridLike(String value) {
            addCriterion("regulatorid like", value, "regulatorid");
            return (Criteria) this;
        }

        public Criteria andRegulatoridNotLike(String value) {
            addCriterion("regulatorid not like", value, "regulatorid");
            return (Criteria) this;
        }

        public Criteria andRegulatoridIn(List<String> values) {
            addCriterion("regulatorid in", values, "regulatorid");
            return (Criteria) this;
        }

        public Criteria andRegulatoridNotIn(List<String> values) {
            addCriterion("regulatorid not in", values, "regulatorid");
            return (Criteria) this;
        }

        public Criteria andRegulatoridBetween(String value1, String value2) {
            addCriterion("regulatorid between", value1, value2, "regulatorid");
            return (Criteria) this;
        }

        public Criteria andRegulatoridNotBetween(String value1, String value2) {
            addCriterion("regulatorid not between", value1, value2, "regulatorid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}