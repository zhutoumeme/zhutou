package cn.zyy.union.pojo;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user_info
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class UserInfo {
    /**
     * Database Column Remarks:
     *   姓名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.NAME
     *
     * @mbg.generated
     */
    private Integer name;

    /**
     * Database Column Remarks:
     *   性别:0-女,1-男
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.sex
     *
     * @mbg.generated
     */
    private Integer sex;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.NAME
     *
     * @return the value of user_info.NAME
     *
     * @mbg.generated
     */
    public Integer getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.NAME
     *
     * @param name the value for user_info.NAME
     *
     * @mbg.generated
     */
    public void setName(Integer name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.sex
     *
     * @return the value of user_info.sex
     *
     * @mbg.generated
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.sex
     *
     * @param sex the value for user_info.sex
     *
     * @mbg.generated
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }
}