package com.nhoclahola.socialnetworkv1.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAdminController
{
    @GetMapping("/admin/dashboard")
    public String dashboard()
    {
        return "admin/admin_dashboard";
    }

    @GetMapping("/admin/manage_user")
    public String manageUser()
    {
        return "Dashboard/ManageUser";
    }
}
