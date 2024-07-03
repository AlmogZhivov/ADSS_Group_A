package com.Superlee.HR.Frontend;

import com.Superlee.HR.Backend.DataAccess.BranchDTO;

import java.util.List;

public class testtest {
    public static void main(String[] args) {
        BranchDTO b = new BranchDTO();
        b.setTestMode(true);
        b.deleteAll();
        List<BranchDTO> l = b.loadAll();
        System.out.println(l.size());

    }
}
