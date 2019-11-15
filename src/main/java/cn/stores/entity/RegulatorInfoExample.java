package cn.stores.entity;

import java.util.ArrayList;
import java.util.List;

public class RegulatorInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RegulatorInfoExample() {
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

        public Criteria andRegulatornameIsNull() {
            addCriterion("regulatorname is null");
            return (Criteria) this;
        }

        public Criteria andRegulatornameIsNotNull() {
            addCriterion("regulatorname is not null");
            return (Criteria) this;
        }

        public Criteria andRegulatornameEqualTo(String value) {
            addCriterion("regulatorname =", value, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andRegulatornameNotEqualTo(String value) {
            addCriterion("regulatorname <>", value, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andRegulatornameGreaterThan(String value) {
            addCriterion("regulatorname >", value, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andRegulatornameGreaterThanOrEqualTo(String value) {
            addCriterion("regulatorname >=", value, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andRegulatornameLessThan(String value) {
            addCriterion("regulatorname <", value, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andRegulatornameLessThanOrEqualTo(String value) {
            addCriterion("regulatorname <=", value, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andRegulatornameLike(String value) {
            addCriterion("regulatorname like", value, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andRegulatornameNotLike(String value) {
            addCriterion("regulatorname not like", value, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andRegulatornameIn(List<String> values) {
            addCriterion("regulatorname in", values, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andRegulatornameNotIn(List<String> values) {
            addCriterion("regulatorname not in", values, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andRegulatornameBetween(String value1, String value2) {
            addCriterion("regulatorname between", value1, value2, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andRegulatornameNotBetween(String value1, String value2) {
            addCriterion("regulatorname not between", value1, value2, "regulatorname");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idIsNull() {
            addCriterion("regulator_role_id is null");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idIsNotNull() {
            addCriterion("regulator_role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idEqualTo(String value) {
            addCriterion("regulator_role_id =", value, "regulator_role_id");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idNotEqualTo(String value) {
            addCriterion("regulator_role_id <>", value, "regulator_role_id");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idGreaterThan(String value) {
            addCriterion("regulator_role_id >", value, "regulator_role_id");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idGreaterThanOrEqualTo(String value) {
            addCriterion("regulator_role_id >=", value, "regulator_role_id");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idLessThan(String value) {
            addCriterion("regulator_role_id <", value, "regulator_role_id");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idLessThanOrEqualTo(String value) {
            addCriterion("regulator_role_id <=", value, "regulator_role_id");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idLike(String value) {
            addCriterion("regulator_role_id like", value, "regulator_role_id");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idNotLike(String value) {
            addCriterion("regulator_role_id not like", value, "regulator_role_id");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idIn(List<String> values) {
            addCriterion("regulator_role_id in", values, "regulator_role_id");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idNotIn(List<String> values) {
            addCriterion("regulator_role_id not in", values, "regulator_role_id");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idBetween(String value1, String value2) {
            addCriterion("regulator_role_id between", value1, value2, "regulator_role_id");
            return (Criteria) this;
        }

        public Criteria andRegulator_role_idNotBetween(String value1, String value2) {
            addCriterion("regulator_role_id not between", value1, value2, "regulator_role_id");
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