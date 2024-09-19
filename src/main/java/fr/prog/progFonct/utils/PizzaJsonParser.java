package fr.prog.progFonct.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import fr.prog.progFonct.domain.Pizza;
import fr.prog.progFonct.domain.PizzaOrder;

public class PizzaJsonParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaJsonParser.class);
	private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();

	public static Optional<List<PizzaOrder>> orderFromJson(String path) {
		try {
			JsonArray jsonArray = (JsonArray) JsonParser.parseReader(new FileReader(path));
			List<PizzaOrder> orderList = gson.fromJson(jsonArray, new TypeToken<List<PizzaOrder>>() {
			}.getType());

			return Optional.of(orderList);
		} catch (JsonIOException | JsonSyntaxException e) {
			LOGGER.error("JSON error : " + e.getMessage());
		} catch (FileNotFoundException e) {
			LOGGER.error("File not found : " + e.getMessage());
		}
		return Optional.empty();
	}

	public static Optional<List<Pizza>> pizzaFromJson(String path) {
		try {
			JsonArray jsonArray = (JsonArray) JsonParser.parseReader(new FileReader(path));
			List<Pizza> orderList = gson.fromJson(jsonArray, new TypeToken<List<Pizza>>() {
			}.getType());

			return Optional.of(orderList);
		} catch (JsonIOException | JsonSyntaxException e) {
			LOGGER.error("JSON error : " + e.getMessage());
		} catch (FileNotFoundException e) {
			LOGGER.error("File not found : " + e.getMessage());
		}
		return Optional.empty();
	}
}
