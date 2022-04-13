package mapping;

import mapping.view.ViewFile;

import java.util.Hashtable;

public interface Inaccessibility {

    Hashtable<Integer, Integer> getInterval();

    ViewFile[] getAllInaccessibilityValue();

    ViewFile getMinPercentOfInaccessibility(int begin, int finish);
}
