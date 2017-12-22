package Lab5;




public class BoundingBox {
    Double xmin;
    Double ymin;
    Double xmax;
    Double ymax;


    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     *
     * @param x - współrzędna x
     * @param y - współrzędna y
     */
    void addPoint(double x, double y) {
        if (this.isEmpty()) {
            xmin = x;
            xmax = x;
            ymin = y;
            ymax = y;
        } else {
            if (x > xmax) xmax = x;
            if (x < xmin) xmin = x;
            if (y > ymax) ymax = y;
            if (y < ymin) ymin = y;
        }

    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     *
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y) {
        return !this.isEmpty() && (xmin <= x && x <= xmax) && (ymin <= y && y <= ymax);
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     *
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb) {
        return !this.isEmpty() && this.xmin < bb.xmin && this.xmax > bb.xmax && this.ymin < bb.ymin && this.ymax > bb.ymax;


    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     *
     * @param bb
     * @return
     */
    boolean intersects(BoundingBox bb) {

        if (this.contains(bb) || this.isEmpty()) return false;
        return this.contains(bb.xmax, bb.ymax) || this.contains(bb.xmin, bb.ymin) || this.contains(bb.xmax, bb.ymin) || this.contains(bb.xmin, bb.ymax) || bb.contains(this.xmax, this.ymax) || bb.contains(this.xmin, this.ymin) || bb.contains(this.xmax, this.ymin) || bb.contains(this.xmin, this.ymax);
    }

    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     *
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb) {

        if (bb.isEmpty()) return this;

        if (this.isEmpty()) {
            this.xmax = bb.xmax;
            this.xmin = bb.xmin;
            this.ymax = bb.ymax;
            this.ymin = bb.ymin;
        } else {
            this.xmax = Double.max(this.xmax, bb.xmax);
            this.xmin = Double.min(this.xmin, bb.xmin);
            this.ymax = Double.max(this.ymax, bb.ymax);
            this.ymin = Double.min(this.ymin, bb.ymin);
        }
        return this;
    }

    /**
     * Sprawdza czy BB jest pusty
     *
     * @return
     */
    boolean isEmpty() {
        return xmax == null || xmin == null || ymax == null || ymin == null;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     *
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX() {
        if (isEmpty()) {
            throw new RuntimeException("no values");
        }
        return (xmax + xmin) / 2;
    }

    /**
     * Oblicza i zwraca współrzędną y środka
     *
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY() {
        if (isEmpty()) {
            throw new RuntimeException("no values");
        }
        return (ymax + ymin) / 2;
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     *
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx) {

        if (this.isEmpty() || bbx.isEmpty())
            throw new RuntimeException("invalid bounding box");

        double earthRadius = 6371.0;
        double dLat = Math.toRadians(this.getCenterY() - bbx.getCenterY());
        double dLng = Math.toRadians(this.getCenterX() - bbx.getCenterX());
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(this.getCenterY())) * Math.cos(Math.toRadians(bbx.getCenterY()));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist;
    }

    public String toString(){
        return "xmax:"+xmax+" xmin:"+xmin+" ymax:"+ymax+" ymin:"+ymin;
    }



}
