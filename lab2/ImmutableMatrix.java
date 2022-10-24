import java.util.ArrayList;
import java.util.List;

public class ImmutableMatrix {
    private List<List<Integer>> _matrix = new ArrayList<>();

    public ImmutableMatrix() {}

    public ImmutableMatrix(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < cols; j++)
                row.add(0);

            _matrix.add(row);
        }
    }

    public ImmutableMatrix(Matrix matrix) {
        List<List<Integer>> newMatrix = new ArrayList<>();

        for (int i = 0; i < matrix.getSize()[0]; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < matrix.getSize()[1]; j++)
                row.add(matrix.get(i, j));

            newMatrix.add(row);
        }

        _matrix = newMatrix;
    }

    public int get(int row, int col) {
        return _matrix.get(row).get(col);
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImmutableMatrix c))
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