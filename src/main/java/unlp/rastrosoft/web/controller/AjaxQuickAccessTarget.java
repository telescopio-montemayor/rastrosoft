/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import unlp.rastrosoft.web.jsonview.Views;
import unlp.rastrosoft.web.model.AjaxResponseListOfLists;
import unlp.rastrosoft.web.model.ExecuteCriteria;
import unlp.rastrosoft.web.model.QuickAccessTargetDB;

/**
 *
 * @author ip300
 */
@RestController
public class AjaxQuickAccessTarget {
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getQuickAccessTargets", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseListOfLists getQuickAccessTargets(@RequestBody ExecuteCriteria execute) {
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();        

        QuickAccessTargetDB quickAccessTargetDB = new QuickAccessTargetDB();
        quickAccessTargetDB.connect();
        
       
        result.addElementos(quickAccessTargetDB.getTargets());
        
        return result;
    }
}
