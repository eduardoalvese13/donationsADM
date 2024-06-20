import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "donations.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("");
            System.out.println(">>> MENU <<<");
            System.out.println("1. Adicionar doação");
            System.out.println("2. Visualizar doações");
            System.out.println("3. Deletar doação");
            System.out.println("4. Sair");
            System.out.print(">> Sua opção: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addDonation();
                    break;
                case 2:
                    viewDonation();
                    break;
                case 3:
                    deleteDonation();
                    break;
                case 4:
                    System.out.println(">> Saindo da aplicação...");
                    break;
                default:
                    System.out.println(">> Opção inválida!");
            }
        } while (choice != 4);

        scanner.close();
    }

    // Método para adicionar uma nova doação

    private static void addDonation() {
        FileWriter writer = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            writer = new FileWriter(FILE_NAME, true);
            bw = new BufferedWriter(writer);
            out = new PrintWriter(bw);


            System.out.println("");
          
            System.out.println(">>> ADICIONAR DOAÇÃO <<<");
            Scanner scanner = new Scanner(System.in);
            System.out.println(">> Digite um nome para a doação: "); 
            String nameDonation = scanner.nextLine();
          
            System.out.println(">> Digite o tipo de doação:");
            String tipoDonation = scanner.nextLine();
          
            System.out.println(">> Digite a quantidade da doação:");
            double qtdDonation = scanner.nextDouble();
          
            System.out.println(">> Digite a data da doação:");
            String dateDonation = scanner.nextLine();
          
            scanner.nextLine();
            out.println(nameDonation + "," + tipoDonation + "," + qtdDonation + "," + dateDonation);
            System.out.println(">> Doação adicionada com sucesso!");
        } catch (IOException e) {
            System.err.println(">> Erro ao adicionar a doação: " + e.getMessage());
        } finally {
            try {
                if (out != null) out.close();
                if (bw != null) bw.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.err.println(">> Erro ao fechar os recursos: " + e.getMessage());
            }
        }
    }

    // Método para visualizar as doações

    private static void viewDonation() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            System.out.println("");
            System.out.println(">>> VISUALIZAR DOAÇÕES <<<");
            System.out.println(">> Doações cadastradas");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println(">> Nome: " + parts[0] + ", Tipo: " + parts[1] + ", Quantidade: " + parts[2] + ", Data: " + parts[2]);
            }
        } catch (IOException e) {
            System.err.println(">> Erro ao visualizar as doações: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                System.err.println(">> Erro ao fechar o leitor: " + e.getMessage());
            }
        }
    }

    // Método para deletar uma doação

    private static void deleteDonation() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("");
            System.out.println(">>> DELETAR DOAÇÃO <<<");
            System.out.println(">> Digite o nome da doação que deseja excluir:");
            String nameDonation = scanner.nextLine();

            File inputFile = new File(FILE_NAME);
            File tempFile = new File("temp.txt");

            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equalsIgnoreCase(nameDonation)) {
                    writer.write(line + "\n");
                } else {
                    found = true;
                }
            }

            if (found) {
                System.out.println(">> Doação excluída com sucesso!");
            } else {
                System.out.println(">> Doação não encontrada na base de dados!");
            }

        } catch (IOException e) {
            System.err.println(">> Erro ao excluir a doação: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.err.println(">> Erro ao fechar os recursos: " + e.getMessage());
            }
        }

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }
}
