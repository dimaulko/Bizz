/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import DAO.DAOImprovementBalance;
import DAO.DAOImprovementEnterprise;
import DAO.DAOImprovementIncasation;
import com.google.gson.Gson;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Improvement;

/**
 *
 * @author Dima
 */
@Stateless
public class EJBGeneralUtil {

    @EJB
    private DAOImprovementIncasation dAOImprovementIncasation;
    @EJB
    private DAOImprovementEnterprise dAOImprovementEnterprise;
    @EJB
    private DAOImprovementBalance dAOImprovementBalance;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String getMapImprovements() {
        Improvement improvement = new Improvement();
        improvement.setBalance(dAOImprovementBalance.getImprovmentBalanceAll());
        improvement.setEnterpriseCount(dAOImprovementEnterprise.getImprovmentEnterpriseAll());
        improvement.setIncasationTime(dAOImprovementIncasation.getImprovmentEnterpriseAll());
        return new Gson().toJson(improvement);
    }
}
