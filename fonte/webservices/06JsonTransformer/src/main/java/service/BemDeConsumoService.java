package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.json.JSONArray;

import dao.ProdutoDAO;
import model.BemDeConsumo;
import model.Produto;
import spark.Request;
import spark.Response;

public class BemDeConsumoService {

	private ProdutoDAO bemDeConsumoDAO;

	public BemDeConsumoService() {
		try {
			bemDeConsumoDAO = new ProdutoDAO("bemdeconsumo.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		String descricao = request.queryParams("descricao");
		float preco = Float.parseFloat(request.queryParams("preco"));
		int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		LocalDateTime dataFabricacao = LocalDateTime.parse(request.queryParams("dataFabricacao"));
		LocalDate dataValidade = LocalDate.parse(request.queryParams("dataValidade"));

		int id = bemDeConsumoDAO.getMaxId() + 1;
		BemDeConsumo bemDeConsumo = new BemDeConsumo(id, descricao, preco, quantidade, dataFabricacao, dataValidade);

		bemDeConsumoDAO.add(bemDeConsumo);

		response.status(201); // 201 Created
        return Integer.valueOf(id);
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		BemDeConsumo bemDeConsumo = (BemDeConsumo) bemDeConsumoDAO.get(id);

		response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
		
        if (bemDeConsumo != null) {
        	
            return bemDeConsumo.toJson();
        } else {
            response.status(404); // 404 Not found
            return new ServiceException("Bem de consumo ID=" + id + " não encontrado.").add("id", id).toJson();
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		BemDeConsumo bemDeConsumo = (BemDeConsumo) bemDeConsumoDAO.get(id);

        if (bemDeConsumo != null) {
        	bemDeConsumo.setDescricao(request.queryParams("descricao"));
        	bemDeConsumo.setPreco(Float.parseFloat(request.queryParams("preco")));
        	bemDeConsumo.setQuant(Integer.parseInt(request.queryParams("quantidade")));
        	bemDeConsumo.setDataFabricacao(LocalDateTime.parse(request.queryParams("dataFabricacao")));
        	bemDeConsumo.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));

        	bemDeConsumoDAO.update(bemDeConsumo);
        	
            return Integer.valueOf(id);
        } else {
            response.status(404); // 404 Not found
    	    response.header("Content-Type", "application/json");
    	    response.header("Content-Encoding", "UTF-8");
            return new ServiceException("Bem de consumo ID=" + id + " não encontrado.").add("id", id).toJson();
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		BemDeConsumo bemDeConsumo = (BemDeConsumo) bemDeConsumoDAO.get(id);

        if (bemDeConsumo != null) {

        	bemDeConsumoDAO.remove(bemDeConsumo);

        	return id;
        } else {
            response.status(404); // 404 Not found
    	    response.header("Content-Type", "application/json");
    	    response.header("Content-Encoding", "UTF-8");
            return new ServiceException("Bem de consumo ID=" + id + " não encontrado.").add("id", id).toJson();
        }
	}

	public Object getAll(Request request, Response response) {
	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
		JSONArray allProds = new JSONArray();
		for (Produto produto : bemDeConsumoDAO.getAll()) {
			BemDeConsumo bemDeConsumo = (BemDeConsumo) produto;
			allProds.put(bemDeConsumo.toJson());
		}

		return allProds;
	}

}
