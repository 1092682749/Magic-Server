package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ClientInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClientInfoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andClientidIsNull() {
            addCriterion("clientid is null");
            return (Criteria) this;
        }

        public Criteria andClientidIsNotNull() {
            addCriterion("clientid is not null");
            return (Criteria) this;
        }

        public Criteria andClientidEqualTo(String value) {
            addCriterion("clientid =", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidNotEqualTo(String value) {
            addCriterion("clientid <>", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidGreaterThan(String value) {
            addCriterion("clientid >", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidGreaterThanOrEqualTo(String value) {
            addCriterion("clientid >=", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidLessThan(String value) {
            addCriterion("clientid <", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidLessThanOrEqualTo(String value) {
            addCriterion("clientid <=", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidLike(String value) {
            addCriterion("clientid like", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidNotLike(String value) {
            addCriterion("clientid not like", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidIn(List<String> values) {
            addCriterion("clientid in", values, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidNotIn(List<String> values) {
            addCriterion("clientid not in", values, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidBetween(String value1, String value2) {
            addCriterion("clientid between", value1, value2, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidNotBetween(String value1, String value2) {
            addCriterion("clientid not between", value1, value2, "clientid");
            return (Criteria) this;
        }

        public Criteria andConnectedIsNull() {
            addCriterion("connected is null");
            return (Criteria) this;
        }

        public Criteria andConnectedIsNotNull() {
            addCriterion("connected is not null");
            return (Criteria) this;
        }

        public Criteria andConnectedEqualTo(Short value) {
            addCriterion("connected =", value, "connected");
            return (Criteria) this;
        }

        public Criteria andConnectedNotEqualTo(Short value) {
            addCriterion("connected <>", value, "connected");
            return (Criteria) this;
        }

        public Criteria andConnectedGreaterThan(Short value) {
            addCriterion("connected >", value, "connected");
            return (Criteria) this;
        }

        public Criteria andConnectedGreaterThanOrEqualTo(Short value) {
            addCriterion("connected >=", value, "connected");
            return (Criteria) this;
        }

        public Criteria andConnectedLessThan(Short value) {
            addCriterion("connected <", value, "connected");
            return (Criteria) this;
        }

        public Criteria andConnectedLessThanOrEqualTo(Short value) {
            addCriterion("connected <=", value, "connected");
            return (Criteria) this;
        }

        public Criteria andConnectedIn(List<Short> values) {
            addCriterion("connected in", values, "connected");
            return (Criteria) this;
        }

        public Criteria andConnectedNotIn(List<Short> values) {
            addCriterion("connected not in", values, "connected");
            return (Criteria) this;
        }

        public Criteria andConnectedBetween(Short value1, Short value2) {
            addCriterion("connected between", value1, value2, "connected");
            return (Criteria) this;
        }

        public Criteria andConnectedNotBetween(Short value1, Short value2) {
            addCriterion("connected not between", value1, value2, "connected");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsIsNull() {
            addCriterion("mostsignbits is null");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsIsNotNull() {
            addCriterion("mostsignbits is not null");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsEqualTo(Long value) {
            addCriterion("mostsignbits =", value, "mostsignbits");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsNotEqualTo(Long value) {
            addCriterion("mostsignbits <>", value, "mostsignbits");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsGreaterThan(Long value) {
            addCriterion("mostsignbits >", value, "mostsignbits");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsGreaterThanOrEqualTo(Long value) {
            addCriterion("mostsignbits >=", value, "mostsignbits");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsLessThan(Long value) {
            addCriterion("mostsignbits <", value, "mostsignbits");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsLessThanOrEqualTo(Long value) {
            addCriterion("mostsignbits <=", value, "mostsignbits");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsIn(List<Long> values) {
            addCriterion("mostsignbits in", values, "mostsignbits");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsNotIn(List<Long> values) {
            addCriterion("mostsignbits not in", values, "mostsignbits");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsBetween(Long value1, Long value2) {
            addCriterion("mostsignbits between", value1, value2, "mostsignbits");
            return (Criteria) this;
        }

        public Criteria andMostsignbitsNotBetween(Long value1, Long value2) {
            addCriterion("mostsignbits not between", value1, value2, "mostsignbits");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsIsNull() {
            addCriterion("leastsignbits is null");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsIsNotNull() {
            addCriterion("leastsignbits is not null");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsEqualTo(Long value) {
            addCriterion("leastsignbits =", value, "leastsignbits");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsNotEqualTo(Long value) {
            addCriterion("leastsignbits <>", value, "leastsignbits");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsGreaterThan(Long value) {
            addCriterion("leastsignbits >", value, "leastsignbits");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsGreaterThanOrEqualTo(Long value) {
            addCriterion("leastsignbits >=", value, "leastsignbits");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsLessThan(Long value) {
            addCriterion("leastsignbits <", value, "leastsignbits");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsLessThanOrEqualTo(Long value) {
            addCriterion("leastsignbits <=", value, "leastsignbits");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsIn(List<Long> values) {
            addCriterion("leastsignbits in", values, "leastsignbits");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsNotIn(List<Long> values) {
            addCriterion("leastsignbits not in", values, "leastsignbits");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsBetween(Long value1, Long value2) {
            addCriterion("leastsignbits between", value1, value2, "leastsignbits");
            return (Criteria) this;
        }

        public Criteria andLeastsignbitsNotBetween(Long value1, Long value2) {
            addCriterion("leastsignbits not between", value1, value2, "leastsignbits");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateIsNull() {
            addCriterion("lastconnecteddate is null");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateIsNotNull() {
            addCriterion("lastconnecteddate is not null");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateEqualTo(Date value) {
            addCriterionForJDBCDate("lastconnecteddate =", value, "lastconnecteddate");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateNotEqualTo(Date value) {
            addCriterionForJDBCDate("lastconnecteddate <>", value, "lastconnecteddate");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateGreaterThan(Date value) {
            addCriterionForJDBCDate("lastconnecteddate >", value, "lastconnecteddate");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastconnecteddate >=", value, "lastconnecteddate");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateLessThan(Date value) {
            addCriterionForJDBCDate("lastconnecteddate <", value, "lastconnecteddate");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastconnecteddate <=", value, "lastconnecteddate");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateIn(List<Date> values) {
            addCriterionForJDBCDate("lastconnecteddate in", values, "lastconnecteddate");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateNotIn(List<Date> values) {
            addCriterionForJDBCDate("lastconnecteddate not in", values, "lastconnecteddate");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastconnecteddate between", value1, value2, "lastconnecteddate");
            return (Criteria) this;
        }

        public Criteria andLastconnecteddateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastconnecteddate not between", value1, value2, "lastconnecteddate");
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