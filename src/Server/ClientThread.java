package Server;

import Server.Handlers.*;
import Server.Handlers.BuyerHandler.*;
import Server.Handlers.ExportHandler.*;
import Server.Handlers.ImportHandler.*;
import Server.Handlers.ProductHandler.*;
import Server.Handlers.SupplierHandler.*;
import Server.Handlers.UsersHandler.*;
import Server.Repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientThread implements Runnable {
    private Socket clientSocket;

    ClientThread(Socket socket, int id) throws IOException {
        this.clientSocket = socket;
    }
    public void run() {
        DbConnectionFactory dbConnectionFactory = new DbConnectionFactoryImpl();
        ProductRepository productsRepository = new ProductsRepositoryImpl(dbConnectionFactory);
        BuyersRepository buyersRepository = new BuyersRepositoryImpl(dbConnectionFactory);
        SuppliersRepository supplierRepository = new SupplierRepositoryImpl(dbConnectionFactory);
        ImportRepository importRepository = new ImportRepositoryImpl(dbConnectionFactory);
        ExportRepository exportRepository = new ExportRepositoryImpl(dbConnectionFactory);
        UsersRepository userRepository = new UsersRepositoryImpl(dbConnectionFactory);

        HashMap<String, Handler> handlersHashMap = new HashMap<String, Handler>();
        handlersHashMap.put("AddProduct", new AddProductHandler(productsRepository));
        handlersHashMap.put("ShowProducts", new ShowProductsHandler(productsRepository));
        handlersHashMap.put("DeleteProduct", new DeleteProductHandler(productsRepository));
        handlersHashMap.put("UpdateProduct", new UpdateProductHandler(productsRepository));
        handlersHashMap.put("SearchProduct", new SearchProductHandler(productsRepository));

        handlersHashMap.put("ShowBuyers", new ShowBuyersHandler(buyersRepository));
        handlersHashMap.put("DeleteBuyer", new DeleteBuyerHandler(buyersRepository));
        handlersHashMap.put("UpdateBuyer", new UpdateBuyerHandler(buyersRepository));
        handlersHashMap.put("SearchBuyer", new SearchBuyerHandler(buyersRepository));
        handlersHashMap.put("AddBuyer", new AddBuyerHandler(buyersRepository));

        handlersHashMap.put("ShowSuppliers", new ShowSuppliersHandler(supplierRepository));
        handlersHashMap.put("DeleteSupplier", new DeleteSupplierHandler(supplierRepository));
        handlersHashMap.put("UpdateSupplier", new UpdateSupplierHandler(supplierRepository));
        handlersHashMap.put("SearchSupplier", new SearchSupplierHandler(supplierRepository));
        handlersHashMap.put("AddSupplier", new AddSupplierHandler(supplierRepository));

        handlersHashMap.put("ShowImport", new ShowImportHandler(importRepository));
        handlersHashMap.put("DeleteImport", new DeleteImportHandler(importRepository));
        handlersHashMap.put("UpdateImport", new UpdateImportHandler(importRepository));
        handlersHashMap.put("SearchImport", new SearchImportHandler(importRepository));
        handlersHashMap.put("AddImport", new AddImportHandler(importRepository));
        handlersHashMap.put("ShowImportByDate", new ShowImportByDateHandler(importRepository));
        handlersHashMap.put("GetPopularImport", new ShowTopImportHandler(importRepository));
        handlersHashMap.put("ShowImportByIDSupplier", new ShowImportByIDSupplierHandler(importRepository));
        handlersHashMap.put("ShowImportByIDUser", new ShowImportByIDUserHandler(importRepository));

        handlersHashMap.put("ShowExport", new ShowExportHandler(exportRepository));
        handlersHashMap.put("DeleteExport", new DeleteExportHandler(exportRepository));
        handlersHashMap.put("UpdateExport", new UpdateExportHandler(exportRepository));
        handlersHashMap.put("SearchExport", new SearchExportHandler(exportRepository));
        handlersHashMap.put("AddExport", new AddExportHandler(exportRepository));
        handlersHashMap.put("ShowExportByDate", new ShowExportByDateHandler(exportRepository));
        handlersHashMap.put("GetPopularExport", new ShowTopExportHandler(exportRepository));
        handlersHashMap.put("ShowExportByIDBuyer", new ShowExportByIDBuyerHandler(exportRepository));
        handlersHashMap.put("ShowExportByIDUser", new ShowExportByIDUserHandler(exportRepository));

        handlersHashMap.put("ShowUsers", new ShowUserHandler(userRepository));
        handlersHashMap.put("DeleteUser", new DeleteUserHandler(userRepository));
        handlersHashMap.put("UpdateUser", new UpdateUserHandler(userRepository));
        handlersHashMap.put("SearchUser", new SearchUserHandler(userRepository));
        handlersHashMap.put("AddUser", new AddUserHandler(userRepository));
        handlersHashMap.put("EntranceUser", new SearchEntranceUserHandler(userRepository));

        BufferedWriter writer;
        BufferedReader reader;
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(
                            clientSocket.getOutputStream()));
            reader = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while(true){
            try {
                String input = reader.readLine();

                JSONObject objInput = (JSONObject)JSONValue.parse(input);
                String route = objInput.get("route").toString();
                String data = objInput.getAsString("data");

                Handler handler = handlersHashMap.get(route);
                Object obj = handler.handle(data);

                ObjectMapper objectMapper = new ObjectMapper();
                String requestJson = objectMapper.writeValueAsString(obj);

                writer.write(requestJson);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//        try {
//            writer.close();
//            reader.close();
//            clientSocket.close();
//            System.out.println("Client #"  + " successfully disconnected.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
