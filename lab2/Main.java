import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
//        // 2 - Пуста матриця, 5 - розмірність матриці
//        Matrix matrix1 = new Matrix();
//        matrix1.show();
//
//        // 2 - Матриця, яка має задані розміри
//        Matrix matrix2 = new Matrix(5, 5);
//        System.out.println("Пуста матриця: ");
//        matrix2.show();
//
//        // 3 - Заповнення матриці значеннями
//        matrix2.fillMatrixWithRandomValues(); // рандомними значеннями
//        // matrix2.fillMatrixWithKeyboard(); // з клавіатури
//        System.out.println("Матриця з заповненими значеннями: ");
//        matrix2.show();
//
//        // 2 - Копія матриці
//        Matrix matrix3 = new Matrix(matrix2);
//        matrix2.set(0, 0, 100);
//        System.out.println("Оригінал матриці з зміненим значенням (0, 0) = 100: ");
//        matrix2.show();
//        System.out.println("Копія матриці: ");
//        matrix3.show();
//
//        // 6 - equals, hashCode
//        System.out.println(matrix3.equals(matrix2));
//        System.out.println(matrix2.hashCode());
//
//        // 4 - Отримання заданого елемента, рядка чи стовпчика
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        // Рядок
//        System.out.print("Оберіть рядок, який ви хочете вивести: ");
//        int row = Integer.parseInt(reader.readLine());
//        System.out.println(matrix2.getRow(row));
//
//        // Стовпчик
//        System.out.print("Оберіть стовпчик, який ви хочете вивести: ");
//        int col = Integer.parseInt(reader.readLine());
//        System.out.println(matrix2.getCol(col));
//
//        // Елемент
//        System.out.print("Оберіть рядок: ");
//        int row = Integer.parseInt(reader.readLine());
//        System.out.print("Оберіть стовпчик: ");
//        int col = Integer.parseInt(reader.readLine());
//        System.out.println(matrix2.get(row, col));
//
//        // 7 - immutable матриця
//        ImmutableMatrix matrix4 = new ImmutableMatrix(matrix3);
//        System.out.println(matrix4.equals(matrix3));
//
//        // 8 (4) - Матриця-стовпчик з використанням статичного методу
//        Matrix matrix = Matrix.createColMatrix(5);
//        matrix.show();
//
//        // 9 (1) -
//        Matrix matrix5 = new Matrix(5, 5);
//        matrix5.fillMatrixWithRandomValues();
//        matrix5.convertToTriangleMatrix("upper");
//        System.out.println("Матриця, перетворена на верхню трикутну: ");
//        matrix5.show();
//
//        Matrix matrix6 = new Matrix(5, 5);
//        matrix6.fillMatrixWithRandomValues();
//        matrix6.convertToTriangleMatrix("lower");
//        System.out.println("Матриця, перетворена на нижню трикутну: ");
//        matrix6.show();
    }
}