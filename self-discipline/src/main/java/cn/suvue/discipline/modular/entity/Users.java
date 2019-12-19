package cn.suvue.discipline.modular.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sd_users")
public class Users implements Serializable {

    private static final long serialVersionUID=1L;

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
     */
    @TableField(value = "user_registration_time",fill = FieldFill.INSERT)
    private LocalDateTime userRegistrationTime;

    /**
     * 用户生日
     */
    @TableField("user_birthday")
    private LocalDate userBirthday;

    /**
     * 用户年龄
     */
    @TableField("user_age")
    private Integer userAge;

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
