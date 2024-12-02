package question_reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuestionReader {

    /**
     * Maps question numbers (starting from 18) to parsed Question objects from a JSON file.
     *
     * @param filePath The file location of the JSON file containing the questions.
     * @return A dictionary where keys are question numbers and values are Question objects.
     */
    public static Map<Integer, Question> parseQuestions(String filePath) {
        String jsonString;
        try {
            jsonString = Files.readString(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> jsonQuestions = List.of();

        try {
            jsonQuestions = objectMapper.readValue(jsonString, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Integer, Question> questionMap = new LinkedHashMap<>();
        int number = 22;

        for (Map<String, Object> jsonQuestion : jsonQuestions) {
            Question question = Question.fromJson(jsonQuestion);
            questionMap.put(number, question);
            number++;
        }

        return questionMap;
    }
}
