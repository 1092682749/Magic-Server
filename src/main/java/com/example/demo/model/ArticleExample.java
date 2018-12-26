package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleExample() {
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

        public Criteria andAidIsNull() {
            addCriterion("aid is null");
            return (Criteria) this;
        }

        public Criteria andAidIsNotNull() {
            addCriterion("aid is not null");
            return (Criteria) this;
        }

        public Criteria andAidEqualTo(Integer value) {
            addCriterion("aid =", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotEqualTo(Integer value) {
            addCriterion("aid <>", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThan(Integer value) {
            addCriterion("aid >", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThanOrEqualTo(Integer value) {
            addCriterion("aid >=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThan(Integer value) {
            addCriterion("aid <", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThanOrEqualTo(Integer value) {
            addCriterion("aid <=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidIn(List<Integer> values) {
            addCriterion("aid in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotIn(List<Integer> values) {
            addCriterion("aid not in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidBetween(Integer value1, Integer value2) {
            addCriterion("aid between", value1, value2, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotBetween(Integer value1, Integer value2) {
            addCriterion("aid not between", value1, value2, "aid");
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

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(Integer value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(Integer value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(Integer value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(Integer value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<Integer> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<Integer> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(Integer value1, Integer value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andKeywordIsNull() {
            addCriterion("keyword is null");
            return (Criteria) this;
        }

        public Criteria andKeywordIsNotNull() {
            addCriterion("keyword is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordEqualTo(String value) {
            addCriterion("keyword =", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotEqualTo(String value) {
            addCriterion("keyword <>", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordGreaterThan(String value) {
            addCriterion("keyword >", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("keyword >=", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordLessThan(String value) {
            addCriterion("keyword <", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordLessThanOrEqualTo(String value) {
            addCriterion("keyword <=", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordLike(String value) {
            addCriterion("keyword like", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotLike(String value) {
            addCriterion("keyword not like", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordIn(List<String> values) {
            addCriterion("keyword in", values, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotIn(List<String> values) {
            addCriterion("keyword not in", values, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordBetween(String value1, String value2) {
            addCriterion("keyword between", value1, value2, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotBetween(String value1, String value2) {
            addCriterion("keyword not between", value1, value2, "keyword");
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