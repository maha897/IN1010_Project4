import java.net.NoRouteToHostException;

public class Prioritetskoe<T extends Comparable<T>> extends LenkeListe<T> {

    public void leggTil(T x) {
        if (stoerrelse() == 0) {
            startListe(x);
            return;
        }

        Node nyNode = new Node(x);
        Node mindreEnn = findNode(nyNode);

        mindreEnn.settInnEtter(nyNode);
        sortNodes();

        lengde++;
    }

    void sortNodes() {
        Node head = start;

        while (head.hentNeste() != null) {
            Node neste = head.hentNeste();
            if (neste.hentElement().compareTo(head.hentElement()) <= 0) {

                // Bytter plass paa 2 nabo noder
                Node tempForrige = head.hentForrige();
                Node tempNeste = neste.hentNeste();

                // Sjekk om den forste noden var den forste, eller har andre som peker paa den
                if (tempForrige != null) {
                    tempForrige.settNeste(neste);
                } else {
                    start = neste;
                }

                // forste node
                neste.settNeste(head);
                neste.settForrige(tempForrige);
                // Andre node
                head.settForrige(neste);
                head.settNeste(tempNeste);

                // printListe();

            }
            head = head.hentNeste();
            if (head == null) {
                return;
            }

        }

    }

    Node findNode(Node settInnNode) {

        // Leter til den finner en den noden som er storre en seg selv og legger den in
        // etter
        Node head = start;
        T compareElem = settInnNode.hentElement();
        while (head.hentNeste() != null) {

            if (head.hentElement().compareTo(compareElem) <= 0) {
                head = head.hentNeste();
                continue;

            }
            if (head.hentElement().compareTo(compareElem) > 0) {
                return head;
            }

        }
        return head;
    }

    void printListe() {
        Node node = start;
        System.out.println("\n");
        while (node != null) {
            System.out.println("ut " + node);
            node = node.hentNeste();
        }

    }
}