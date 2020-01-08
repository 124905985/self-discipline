package cn.suvue.discipline.practice.stream;

import cn.hutool.json.JSONUtil;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * stream流的map用法
 *
 * @author suvue
 * @date 2020/1/8
 */

@Builder
class Org {
    //主键id
    private Long id;

    //父部门id
    private Long pid;

    //父级ids
    private String pids;

    //名称
    private String name;

    //组织类型（1：公司 2：部门）
    private Integer orgType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }
}

public class StreamMapOperation {
    //组织结构集合
    private static List<Org> orgList = new ArrayList<>();

    static {
        Org comp1 = Org.builder().id(1L).name("一公司").pid(0L).pids("0L").orgType(1).build();
        Org dept1 = Org.builder().id(2L).name("一公司的一部门").pid(1L).pids("0,1").orgType(2).build();
        Org dept2 = Org.builder().id(3L).name("一公司的二部门").pid(1L).pids("0,1").orgType(2).build();
        Org dept3 = Org.builder().id(4L).name("一公司的三部门").pid(1L).pids("0,1").orgType(2).build();
        Org comp2 = Org.builder().id(5L).name("二公司").pid(0L).pids("0L").orgType(1).build();
        Org dept4 = Org.builder().id(6L).name("二公司的一部门").pid(5L).pids("0,5").orgType(2).build();
        Org dept5 = Org.builder().id(7L).name("二公司的二部门").pid(5L).pids("0,5").orgType(2).build();
        Org dept6 = Org.builder().id(8L).name("二公司的三部门").pid(5L).pids("0,5").orgType(2).build();
        orgList.add(comp1);
        orgList.add(dept1);
        orgList.add(dept2);
        orgList.add(dept3);
        orgList.add(comp2);
        orgList.add(dept4);
        orgList.add(dept5);
        orgList.add(dept6);
    }

    public static void main(String[] args) {
        Map<String, Map<String, Long>> mapMap = orgList.stream()
                .filter(c -> c.getOrgType() == 1)
                .collect(Collectors.toMap(
                        Org::getName,
                        c -> orgList.stream()
                                .filter(d -> d.getOrgType() == 2 && d.getPids().contains(String.valueOf(c.getId())))
                                .collect(Collectors.toMap(
                                        new Function<Org, String>() {
                                            @Override
                                            public String apply(Org o) {
                                                return o.getName();
                                            }
                                        },
                                        new Function<Org, Long>() {
                                            @Override
                                            public Long apply(Org org) {
                                                return org.getId();
                                            }
                                        }
                                ))

                ));

        System.out.println(JSONUtil.toJsonStr(mapMap));
    }
}
