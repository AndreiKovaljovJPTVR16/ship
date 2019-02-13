/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Buyer;
import entity.Role;
import entity.User;
import entity.UserRoles;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BuyerFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;
import utils.Encription;

/**
 *
 * @author pupil
 */
@WebServlet(name = "SecurityServlet",loadOnStartup = 1, urlPatterns = 
        {"/showLogin",
            "/login",
            "/registration",
            "/showRegistration",
            "/changePassword",
            "/showChangePassword",
            "/logout",
            
        
})
public class SecurityServlet extends HttpServlet {

@EJB private BuyerFacade buyerFacade;
@EJB private UserFacade userFacade;
@EJB private RoleFacade roleFacade;
@EJB private UserRolesFacade userRolesFacade;

    @Override
    public void init() throws ServletException {
        List<User> listUsers = userFacade.findAll();
        if (listUsers.size()!=0) {return;}
        Buyer buyer = new Buyer("armaggedonchik@gmail.com","Andrei","Kovaljov",1000,null);
        
        buyerFacade.create(buyer);
        
        Encription encrioption = new Encription();
        String password = encrioption.getEncriptionPass("admin");
        User user = new User("admin",password,true, buyer);
        userFacade.create(user);
        Role role = new Role("ADMINISTRATOR");
        roleFacade.create(role);
        UserRoles ur = new UserRoles();
        ur.setRole(role);
        ur.setUser(user);
        userRolesFacade.create(ur);
        role.setName("USER");
        roleFacade.create(role);    
            
            
        
    }


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        HttpSession session = null;
        
        
        if(path != null){
            switch (path) {
                case "/showLogin":
                    request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                    break;
                case "/showRegistration":
                    request.getRequestDispatcher("/showRegistration.jsp").forward(request, response);
                case "/login":
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    User regUser = userFacade.findUserByLogin(login);// нашли юзера по логину в базе
                    if(regUser==null){
                        request.setAttribute("info", "Войдите");
                        request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                       
                    }
                    
                    Encription encription = new Encription();
                    String encriptPassword = encription.getEncriptionPass(password);
                    
                    
                    if(!encriptPassword.equals(regUser.getPassword())){ // 
                        request.setAttribute("info", "Неправильный логин или пароль");
                        request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                    }
                    session = request.getSession(true);
                   session.setAttribute("regUser",regUser);
                   request.setAttribute("info", regUser.getBuyer().getName()+", вы вошли как - "+regUser.getLogin());
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;        
                 case "/registration":
                    String name = request.getParameter("name");
                    String surname  = request.getParameter("surname");
                    String email = request.getParameter("email");
                    login = request.getParameter("login");
                    String password1 = request.getParameter("password1");
                    String password2 = request.getParameter("password2");

                    if(!password1.equals(password2)){
                        request.setAttribute("info", "Не совпадает пароль");
                        request.getRequestDispatcher("/showRegistration.jsp").forward(request, response);
                    }
                    Buyer buyer = new Buyer(email,name,surname,0,null);
                    buyerFacade.create(buyer);
                    
                     encription = new Encription();
                    encriptPassword = encription.getEncriptionPass(password1);
                    User user = new User(login, encriptPassword, true, buyer);
                    userFacade.create(user);
                    UserRoles ur= new UserRoles();
                    ur.setUser(user);
                    Role role = roleFacade.findByName("USER");
                    ur.setRole(role);
                    userRolesFacade.create(ur);


                request.setAttribute("info", "Регистрация успешна!");
                request.getRequestDispatcher("/index.html").forward(request, response);
                break;  
                case "/showChangePassword":
                    session = request.getSession(false);
                    if(session == null){
                request.setAttribute("info", "Войдите!");
                request.getRequestDispatcher("/showLogin").forward(request, response);
            }
             regUser = (User) session.getAttribute("regUser");
            if(regUser == null){
                request.setAttribute("info", "Войдите!");
                request.getRequestDispatcher("/showLogin").forward(request, response);
            }
                    request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                    break;
                case "/changePassword":
                      session = request.getSession(false);
            if(session == null){
                request.setAttribute("info", "Войдите!");
                request.getRequestDispatcher("/showLogin").forward(request, response);
            }
             regUser = (User) session.getAttribute("regUser");
            if(regUser == null){
                request.setAttribute("info", "Войдите!");
                request.getRequestDispatcher("/showLogin").forward(request, response);
            }
                    
                    String oldPassword = request.getParameter("oldPassword");
                    password = request.getParameter("oldPassword");
                    encription = new Encription();
                   String encriptOldPassword = encription.getEncriptionPass(oldPassword);
                    if (!encriptOldPassword.equals(regUser.getPassword())) {
                        
                        request.setAttribute("info", "Неправильный текущий пароль");
                        request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                        break;
                        
                    }
                    String newPassword1 = request.getParameter("newPassword1");
                    String newPassword2 = request.getParameter("newPassword2");
                    if (newPassword1.equals(newPassword2)) {
                        
                        regUser.setPassword(encription.getEncriptionPass(newPassword1));
                        
                        
                    }
                    request.setAttribute("info", "Вы успешно изменили пароль");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                case "/logout":
                     session = request.getSession(false);
                    if(session != null){
                        session.invalidate();
                        request.setAttribute("info", "Вы вышли!");
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                    break;

            }
            
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
