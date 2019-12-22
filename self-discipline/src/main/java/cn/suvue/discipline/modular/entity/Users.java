package cn.suvue.discipline.modular.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
@Data
@TableName("sd_users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 无参构造函数
     */
    public Users() {

    }

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 用户IP
     */
    @TableField("user_ip")
    private String userIp;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户密码
     */
    @TableField("user_password")
    private String userPassword;

    /**
     * 用户密码盐值
     */
    @TableField("user_salt")
    private String userSalt;

    /**
     * 用户邮箱
     */
    @TableField("user_email")
    private String userEmail;

    /**
     * 用户头像
     */
    @TableField("user_profile_photo")
    private String userProfilePhoto;

    /**
     * 注册时间
     * JsonFormat将类似2019-12-21T23:10:32的时间转换成中国时区
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "user_registration_time", fill = FieldFill.INSERT)
    private LocalDateTime userRegistrationTime;

    /**
     * 用户生日
     */
    @TableField("user_birthday")
    private LocalDateTime userBirthday;

    /**
     * 用户手机号
     */
    @TableField("user_telephone_number")
    private Integer userTelephoneNumber;

    /**
     * 用户昵称
     */
    @TableField("user_nickname")
    private String userNickname;


}
