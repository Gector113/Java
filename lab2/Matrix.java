import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Matrix {
    private List<List<Integer>> _matrix = new ArrayList<>();

    public Matrix() {}

    public Matrix(int rows, int cols) {
        // проходимось по рядкам
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();

            // проходимось по стовпцям кожного рядка
            for (int j = 0; j < cols; j++)
                row.add(0);

            // додаємо рядок в матрицю
            _matrix.add(row);
        }
    }

    public Matrix(Matrix matrix) {
        List<List<Integer>> newMatrix = new ArrayList<>();

        for (int i = 0; i < matrix.getSize()[0]; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < matrix.getSize()[1]; j++)
                row.add(matrix.get(i, j));

            newMatrix.add(row);
        }

        _matrix = newMatrix;
    }

    public Matrix(List<List<Integer>> matrix) {
        _matrix = new ArrayList<>();

        for (List<Integer> integers : matrix) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < matrix.get(0).size(); j++) {
                row.add(integers.get(j));
            }
            _matrix.add(row);
        }
    }

    public void fillMatrixWithRandomValues() {
        for (List<Integer> row : _matrix)
            for (int j = 0; j < _matrix.get(0).size(); j++)
                row.set(j, new Random().nextInt(10));
    }

    public void fillMatrixWithKeyboard() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        if (_matrix.isEmpty()) {
            System.out.println("Введіть кількість рядків: ");
            int rows = Integer.parseInt(reader.readLine());
            System.out.println("Введіть кількість стовпців: ");
            int cols = Integer.parseInt(reader.readLine());

            for (int i = 0; i < rows; i++) {
                List<Integer> row = new ArrayList<>();
                System.out.println("Введіть рядок " + i + ": ");

                for (int j = 0; j < cols; j++) {
                    System.out.println("Введіть значення " + j + " рядка " + i + ": ");
                    row.add(Integer.valueOf(reader.readLine()));
                }

                _matrix.add(row);
            }

            return;
        }

        for (int i = 0; i < _matrix.size(); i++) {
            System.out.println("Введіть рядок " + i + ": ");

            for (int j = 0; j < _matrix.get(0).size(); j++) {
                System.out.print("Введіть значення " + j + " рядка " + i + ": ");
                _matrix.get(i).set(j, Integer.valueOf(reader.readLine()));
            }
        }
    }

    public int get(int row, int col) {
        return _matrix.get(row).get(col);
    }

    public void set(int row, int col, int value) {
        _matrix.get(row).set(col, value);
    }

    public List<Integer> getRow(int row) {
        return _matrix.get(row);
    }

    public List<Integer> getCol(int col) {
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < _matrix.size(); i++)
            values.add(get(i, col));

        return values;
    }

    public int[] getSize() {
        return new int[] { _matrix.size(), _matrix.get(0).size() };
    }

    public List<List<Integer>> getMatrix() {
        return _matrix;
    }

    public void show() {
        System.out.println(this);
    }

    public static Matrix createColMatrix(int height) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(new Random().nextInt(10));
            result.add(row);
        }

        return new Matrix(result);
    }

    public void convertToTriangleMatrix(String type) {
        if (Objects.equals(type, "upper"))
            convertToUpperTriangleMatrix();
        else if (Objects.equals(type, "lower"))
            convertToLowerTriangleMatrix();
    }

    private void convertToUpperTriangleMatrix() {
        for (int i = 0; i < _matrix.size(); i++)
            for (int j = 0; j < i; j++)
                this.set(i, j, 0);
    }

    private void convertToLowerTriangleMatrix() {
        for (int i = 0; i < _matrix.size(); i++)
            for (int j = i + 1; j < _matrix.get(0).size(); j++)
                this.set(i, j, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Matrix c))
            return false;

        if (c.getSize()[0] != getSize()[0])
            return false;

        if (c.getSize()[1] != getSize()[1])
            return false;

        for (int i = 0; i < c.getSize()[0]; i++)
            for (int j = 0; j < c.getSize()[1]; j++)
                if (c.get(i, j) != get(i, j))
                    return false;

        return true;
    }

    @Override
    public int hashCode() {
        return _matrix.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (!_matrix.isEmpty())
            result.append(getSize()[0]).append("x").append(getSize()[1]).append("\n");

        for (int i = 0; i < _matrix.size(); i++) {
            for (int j = 0; j < _matrix.get(0).size(); j++)
                result.append(get(i, j)).append("\t");

            result.append("\n");
        }

        return result.toString();
    }
}