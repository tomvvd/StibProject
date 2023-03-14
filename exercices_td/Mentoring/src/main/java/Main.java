import dto.StudentDto;
import exception.RepositoryException;
import repository.StudentRepository;

import java.util.List;

public class Main {

    public static void printList(List<?> list){
        for (var l:list) {
            System.out.println(l);
        }
        System.out.println("");
    }

    public static void main(String[] args) throws RepositoryException {
        StudentRepository stdRepo = new StudentRepository();

        printList(stdRepo.getAll());
        //ajouter un élément nouveau
        stdRepo.add(new StudentDto(58594,"Vervondel","Thomas"));
        printList(stdRepo.getAll());
        //modifier un élément existant
        stdRepo.add(new StudentDto(82227,"Scolari","Elio"));
        printList(stdRepo.getAll());
        //supprimer un élément
        stdRepo.remove(58594);
        printList(stdRepo.getAll());
    }
}
