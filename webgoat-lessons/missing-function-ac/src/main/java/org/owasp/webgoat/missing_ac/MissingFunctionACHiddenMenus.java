/*
 * This file is part of WebGoat, an Open Web Application Security Project utility. For details, please see http://www.owasp.org/
 *
 * Copyright (c) 2002 - 2019 Bruce Mayhew
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * Getting Source ==============
 *
 * Source for this application is maintained at https://github.com/WebGoat/WebGoat, a repository for free software projects.
 */

package org.owasp.webgoat.missing_ac;

import com.google.common.collect.Lists;
import org.owasp.webgoat.assignments.AssignmentEndpoint;
import org.owasp.webgoat.assignments.AssignmentHints;
import org.owasp.webgoat.assignments.AssignmentPath;
import org.owasp.webgoat.assignments.AttackResult;
import org.owasp.webgoat.session.UserSessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

/**
 * Created by jason on 1/5/17.
 */
@RestController
@AssignmentHints({"access-control.hidden-menus.hint1","access-control.hidden-menus.hint2","access-control.hidden-menus.hint3"})
public class MissingFunctionACHiddenMenus extends AssignmentEndpoint {
    //UserSessionData is bound to session and can be used to persist data across multiple assignments
    @Autowired
    UserSessionData userSessionData;


    @PostMapping(path = "/access-control/hidden-menu", produces = {"application/json"})
    @ResponseBody
    public AttackResult completed(String hiddenMenu1, String hiddenMenu2) {
        //overly simple example for success. See other existing lesssons for ways to detect 'success' or 'failure'
        if (hiddenMenu1.equals("Users") && hiddenMenu2.equals("Config")) {
            return trackProgress(success()
                    .output("")
                    .feedback("access-control.hidden-menus.success")
                    .build());
        }

        if (hiddenMenu1.equals("Config") && hiddenMenu2.equals("Users")) {
            return trackProgress(failed()
                    .output("")
                    .feedback("access-control.hidden-menus.close")
                    .build());
        }

        return trackProgress(failed()
                .feedback("access-control.hidden-menus.failure")
                .output("")
                .build());
    }
}
