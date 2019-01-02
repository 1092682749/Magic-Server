package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ArticleNotifyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleNotifyExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAtitleIsNull() {
            addCriterion("atitle is null");
            return (Criteria) this;
        }

        public Criteria andAtitleIsNotNull() {
            addCriterion("atitle is not null");
            return (Criteria) this;
        }

        public Criteria andAtitleEqualTo(String value) {
            addCriterion("atitle =", value, "atitle");
            return (Criteria) this;
        }

        public Criteria andAtitleNotEqualTo(String value) {
            addCriterion("atitle <>", value, "atitle");
            return (Criteria) this;
        }

        public Criteria andAtitleGreaterThan(String value) {
            addCriterion("atitle >", value, "atitle");
            return (Criteria) this;
        }

        public Criteria andAtitleGreaterThanOrEqualTo(String value) {
            addCriterion("atitle >=", value, "atitle");
            return (Criteria) this;
        }

        public Criteria andAtitleLessThan(String value) {
            addCriterion("atitle <", value, "atitle");
            return (Criteria) this;
        }

        public Criteria andAtitleLessThanOrEqualTo(String value) {
            addCriterion("atitle <=", value, "atitle");
            return (Criteria) this;
        }

        public Criteria andAtitleLike(String value) {
            addCriterion("atitle like", value, "atitle");
            return (Criteria) this;
        }

        public Criteria andAtitleNotLike(String value) {
            addCriterion("atitle not like", value, "atitle");
            return (Criteria) this;
        }

        public Criteria andAtitleIn(List<String> values) {
            addCriterion("atitle in", values, "atitle");
            return (Criteria) this;
        }

        public Criteria andAtitleNotIn(List<String> values) {
            addCriterion("atitle not in", values, "atitle");
            return (Criteria) this;
        }

        public Criteria andAtitleBetween(String value1, String value2) {
            addCriterion("atitle between", value1, value2, "atitle");
            return (Criteria) this;
        }

        public Criteria andAtitleNotBetween(String value1, String value2) {
            addCriterion("atitle not between", value1, value2, "atitle");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(String value) {
            addCriterion("addtime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(String value) {
            addCriterion("addtime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(String value) {
            addCriterion("addtime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(String value) {
            addCriterion("addtime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(String value) {
            addCriterion("addtime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(String value) {
            addCriterion("addtime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLike(String value) {
            addCriterion("addtime like", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotLike(String value) {
            addCriterion("addtime not like", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<String> values) {
            addCriterion("addtime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<String> values) {
            addCriterion("addtime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(String value1, String value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(String value1, String value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andReadnumberIsNull() {
            addCriterion("readnumber is null");
            return (Criteria) this;
        }

        public Criteria andReadnumberIsNotNull() {
            addCriterion("readnumber is not null");
            return (Criteria) this;
        }

        public Criteria andReadnumberEqualTo(Integer value) {
            addCriterion("readnumber =", value, "readnumber");
            return (Criteria) this;
        }

        public Criteria andReadnumberNotEqualTo(Integer value) {
            addCriterion("readnumber <>", value, "readnumber");
            return (Criteria) this;
        }

        public Criteria andReadnumberGreaterThan(Integer value) {
            addCriterion("readnumber >", value, "readnumber");
            return (Criteria) this;
        }

        public Criteria andReadnumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("readnumber >=", value, "readnumber");
            return (Criteria) this;
        }

        public Criteria andReadnumberLessThan(Integer value) {
            addCriterion("readnumber <", value, "readnumber");
            return (Criteria) this;
        }

        public Criteria andReadnumberLessThanOrEqualTo(Integer value) {
            addCriterion("readnumber <=", value, "readnumber");
            return (Criteria) this;
        }

        public Criteria andReadnumberIn(List<Integer> values) {
            addCriterion("readnumber in", values, "readnumber");
            return (Criteria) this;
        }

        public Criteria andReadnumberNotIn(List<Integer> values) {
            addCriterion("readnumber not in", values, "readnumber");
            return (Criteria) this;
        }

        public Criteria andReadnumberBetween(Integer value1, Integer value2) {
            addCriterion("readnumber between", value1, value2, "readnumber");
            return (Criteria) this;
        }

        public Criteria andReadnumberNotBetween(Integer value1, Integer value2) {
            addCriterion("readnumber not between", value1, value2, "readnumber");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andIsbannerIsNull() {
            addCriterion("isbanner is null");
            return (Criteria) this;
        }

        public Criteria andIsbannerIsNotNull() {
            addCriterion("isbanner is not null");
            return (Criteria) this;
        }

        public Criteria andIsbannerEqualTo(Integer value) {
            addCriterion("isbanner =", value, "isbanner");
            return (Criteria) this;
        }

        public Criteria andIsbannerNotEqualTo(Integer value) {
            addCriterion("isbanner <>", value, "isbanner");
            return (Criteria) this;
        }

        public Criteria andIsbannerGreaterThan(Integer value) {
            addCriterion("isbanner >", value, "isbanner");
            return (Criteria) this;
        }

        public Criteria andIsbannerGreaterThanOrEqualTo(Integer value) {
            addCriterion("isbanner >=", value, "isbanner");
            return (Criteria) this;
        }

        public Criteria andIsbannerLessThan(Integer value) {
            addCriterion("isbanner <", value, "isbanner");
            return (Criteria) this;
        }

        public Criteria andIsbannerLessThanOrEqualTo(Integer value) {
            addCriterion("isbanner <=", value, "isbanner");
            return (Criteria) this;
        }

        public Criteria andIsbannerIn(List<Integer> values) {
            addCriterion("isbanner in", values, "isbanner");
            return (Criteria) this;
        }

        public Criteria andIsbannerNotIn(List<Integer> values) {
            addCriterion("isbanner not in", values, "isbanner");
            return (Criteria) this;
        }

        public Criteria andIsbannerBetween(Integer value1, Integer value2) {
            addCriterion("isbanner between", value1, value2, "isbanner");
            return (Criteria) this;
        }

        public Criteria andIsbannerNotBetween(Integer value1, Integer value2) {
            addCriterion("isbanner not between", value1, value2, "isbanner");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(String value) {
            addCriterion("uid like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(String value) {
            addCriterion("uid not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andSourceFromIsNull() {
            addCriterion("source_from is null");
            return (Criteria) this;
        }

        public Criteria andSourceFromIsNotNull() {
            addCriterion("source_from is not null");
            return (Criteria) this;
        }

        public Criteria andSourceFromEqualTo(String value) {
            addCriterion("source_from =", value, "sourceFrom");
            return (Criteria) this;
        }

        public Criteria andSourceFromNotEqualTo(String value) {
            addCriterion("source_from <>", value, "sourceFrom");
            return (Criteria) this;
        }

        public Criteria andSourceFromGreaterThan(String value) {
            addCriterion("source_from >", value, "sourceFrom");
            return (Criteria) this;
        }

        public Criteria andSourceFromGreaterThanOrEqualTo(String value) {
            addCriterion("source_from >=", value, "sourceFrom");
            return (Criteria) this;
        }

        public Criteria andSourceFromLessThan(String value) {
            addCriterion("source_from <", value, "sourceFrom");
            return (Criteria) this;
        }

        public Criteria andSourceFromLessThanOrEqualTo(String value) {
            addCriterion("source_from <=", value, "sourceFrom");
            return (Criteria) this;
        }

        public Criteria andSourceFromLike(String value) {
            addCriterion("source_from like", value, "sourceFrom");
            return (Criteria) this;
        }

        public Criteria andSourceFromNotLike(String value) {
            addCriterion("source_from not like", value, "sourceFrom");
            return (Criteria) this;
        }

        public Criteria andSourceFromIn(List<String> values) {
            addCriterion("source_from in", values, "sourceFrom");
            return (Criteria) this;
        }

        public Criteria andSourceFromNotIn(List<String> values) {
            addCriterion("source_from not in", values, "sourceFrom");
            return (Criteria) this;
        }

        public Criteria andSourceFromBetween(String value1, String value2) {
            addCriterion("source_from between", value1, value2, "sourceFrom");
            return (Criteria) this;
        }

        public Criteria andSourceFromNotBetween(String value1, String value2) {
            addCriterion("source_from not between", value1, value2, "sourceFrom");
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