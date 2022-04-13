import mapping.Inaccessibility;
import mapping.InaccessibilityImpl;
import mapping.view.ViewFile;

import java.util.*;
import java.util.stream.Collectors;


public class main {

    private final static Inaccessibility inaccessibilityImpl = new InaccessibilityImpl();
    public static void main(String[] args) {

        ViewFile[] viewFile = inaccessibilityImpl.getAllInaccessibilityValue();
        System.out.println("Не соответствующие Put запросы\n");
        List<ViewFile> viewFileNotNull = Arrays.stream(viewFile).filter(Objects::nonNull).sorted().collect(Collectors.toList());
        for (ViewFile view :viewFileNotNull) {System.out.println(view);}

    }

}
