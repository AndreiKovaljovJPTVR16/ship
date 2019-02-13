/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Buyer;
import entity.Product;
import entity.Purchase;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BuyerFacade;
import session.ProductFacade;
import session.PurchaseFacade;
import session.UserRolesFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "MyServlet", urlPatterns = {
    "/showListProducts",
    "/showListBuyers",
    "/showPageForGiveProduct",
    "/makePurchase",
    "/showNewProduct",
    "/addNewProduct",
    "/showAddBuyer",
    "/addNewBuyer",
    "/addMoney",
    "/ShowAddMoney"
   

})

public class MyServlet extends HttpServlet {
    
    @EJB private BuyerFacade buyerFacade;
    @EJB private ProductFacade productFacade;
    @EJB private PurchaseFacade purchaseFacade;
    @EJB private UserRolesFacade userRolesFacade;
    

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
         String path = request.getServletPath();
         if ("/showListProducts".equals(path)) {
            List<Product> listProducts = productFacade.findAll();
             request.setAttribute("listProducts", listProducts);
             
             request.getRequestDispatcher("/WEB-INF/showListProducts.jsp").forward(request, response);
        } if ("/showListBuyers".equals(path)) {
             HttpSession session = request.getSession(false);
             
            if (session==null) {
                request.setAttribute("info", "Войдите");
                request.getRequestDispatcher("/showLogin").forward(request, response);
                
            }
            User regUser = (User) session.getAttribute("regUser");
            
            if (regUser==null) {
                
                request.setAttribute("info", "Войдите");
                request.getRequestDispatcher("/showLogin").forward(request, response);
                
            }
            
            
            
            
            Boolean isRole = userRolesFacade.isRole("ADMINISTRATOR", regUser);
            if (!isRole) {
                request.setAttribute("info", "Вы должны быть администратором");
                request.getRequestDispatcher("/showLogin").forward(request, response);;
            }
            
            
            List<Buyer> listBuyers = buyerFacade.findAll();
             request.setAttribute("listBuyers", listBuyers);
        
                
                
            
            
                
             request.getRequestDispatcher("/WEB-INF/showListBuyers.jsp").forward(request, response);
             
        }
         else if("/showNewProduct".equals(path)){
             HttpSession session = request.getSession(false);
            if (session==null) {
                request.setAttribute("info", "Войдите");
                request.getRequestDispatcher("/showLogin");
                
            }
            User regUser = (User) session.getAttribute("regUser");
            
            if (regUser==null) {
                
                request.setAttribute("info", "Войдите");
                request.getRequestDispatcher("/showLogin");
                
            }
            
            Boolean isRole = userRolesFacade.isRole("ADMINISTRATOR", regUser);
            if (!isRole) {
                request.setAttribute("info", "Вы должны быть администратором");
                request.getRequestDispatcher("/showLogin").forward(request, response);;
            }
            
            
            
            
            request.getRequestDispatcher("/WEB-INF/showNewProduct.jsp").forward(request, response);
        }else if("/addNewProduct".equals(path)){
            
            
                String name = request.getParameter("name");
            String prices = request.getParameter("price");
            int price = Integer.parseInt(prices);
            String counts = request.getParameter("count");
            int count = Integer.parseInt(counts);
            Product product = new Product(name,price,count);
            productFacade.create(product);
            request.setAttribute("info"," Товар: \""+product.getName()+"\" добавлен ");
            
            
           
            
            request.getRequestDispatcher("/WEB-INF/showNewProduct.jsp").forward(request, response);
        }
         else if("/showAddBuyer".equals(path)){
           
            request.getRequestDispatcher("/WEB-INF/showAddBuyer.jsp").forward(request, response);
        }
        else if("/addNewBuyer".equals(path)){
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String cashs = request.getParameter("cash");
            int cash = Integer.parseInt(cashs);
            //Buyer buyer = new Buyer(name,surname,cash,null);
            
            //buyerFacade.create(buyer);
            
            request.getRequestDispatcher("/WEB-INF/showAddBuyer.jsp").forward(request, response);
        }else if ("/showPageForGiveProduct".equals(path)) {
            HttpSession session = request.getSession(false);
            if(session == null){
                request.setAttribute("info", "Войдите!");
                request.getRequestDispatcher("/showLogin").forward(request, response);
            }
            User regUser = (User) session.getAttribute("regUser");
            if(regUser == null){
                request.setAttribute("info", "Войдите!");
                request.getRequestDispatcher("/showLogin").forward(request, response);
            }
            List<Product> listProducts = productFacade.findAll();
            
            request.setAttribute("listProducts", listProducts);
            
            
                
             request.getRequestDispatcher("/WEB-INF/showPageForGiveProduct.jsp").forward(request, response);
        }else if("/makePurchase".equals(path)){
              HttpSession session = request.getSession(false);
            User regUser = (User) session.getAttribute("regUser");
            
            String productId = request.getParameter("productId");
            String buyerId = request.getParameter("buyerId");
            String purchaseCounts = request.getParameter("purchaseCount");
            int purchaseCount = Integer.parseInt(purchaseCounts);
            Product product = productFacade.find(new Long(productId));
            Buyer buyer = buyerFacade.find(new Long(regUser.getBuyer().getId()));
            
            Calendar c = new GregorianCalendar();
            
                
                        
                        if (buyer.getCash() > (purchaseCount * product.getPrice()) || buyer.getCash() == (purchaseCount * product.getPrice()) ) {
            if(product.getCount()-purchaseCount > 0 )  {
                
                product.setCount(product.getCount()-purchaseCount);
                
                buyer.setCash(buyer.getCash()-(purchaseCount*product.getPrice()));
               
               buyerFacade.edit(buyer);
               productFacade.edit(product);
                Purchase purchase = new Purchase(product,buyer, c.getTime(),purchaseCount);
                purchaseFacade.create(purchase);
                request.setAttribute("info","Товар \""+product.getName()+"\" куплен");
            }else{
                request.setAttribute("info","Товаров не осталось");
            }
           
        }else{
             request.setAttribute("info","Не хватает денег");
        }
           
           
        
    
                
             
           request.getRequestDispatcher("/WEB-INF/showPageForGiveProduct.jsp").forward(request, response);   
            }
        else if("/ShowAddMoney".equals(path)){
                 HttpSession session = request.getSession(false);
            if(session == null){
                request.setAttribute("info", "Войдите!");
                request.getRequestDispatcher("/showLogin").forward(request, response);
            }
            User regUser = (User) session.getAttribute("regUser");
            if(regUser == null){
                request.setAttribute("info", "Войдите!");
                request.getRequestDispatcher("/showLogin").forward(request, response);
            }
            
            
            
                
             request.getRequestDispatcher("addMoney.jsp").forward(request, response);
            }
        else if("/addMoney".equals(path)){
            HttpSession session = request.getSession(false);
            if (session==null) {
                request.setAttribute("info", "Войдите");
                request.getRequestDispatcher("/showLogin");
                
            }
            User regUser = (User) session.getAttribute("regUser");
            
            if (regUser==null) {
                
                request.setAttribute("info", "Войдите");
                request.getRequestDispatcher("/showLogin");
                
            }
            
            String money = request.getParameter("money");
            int cash = Integer.parseInt(money);
            Buyer buyer = buyerFacade.find(regUser.getBuyer().getId());
            buyer.setCash(buyer.getCash()+cash);
            buyerFacade.edit(buyer);
            request.setAttribute("info", "Средства добавлены");
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }
           
           
        }
//         else if ("/showListReaders".equals(path)) {
//            List<Reader> listReaders = readerFacade.findAll();
//            request.setAttribute("listReaders", listReaders);
//                request.setAttribute("info", "page3,Привет из сервлета!");
//             request.getRequestDispatcher("/WEB-INF/showListReaders.jsp").forward(request, response);
    

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