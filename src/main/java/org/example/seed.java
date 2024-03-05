package org.example;

import org.example.model.Box;
import org.example.model.Category;
import org.example.model.Item;
import org.example.model.User;
import org.hibernate.Session;

public class seed {
    public static void seed() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Create categories
        Category c1 = new Category("navidad");
        Category c2 = new Category("deporte");
        Category c3 = new Category("electrónica");
        Category c4 = new Category("varios");

        session.persist(c1);
        session.persist(c2);
        session.persist(c3);
        session.persist(c4);


        // Create boxes
        Box b1 = new Box("navidad", "trastero");
        Box b2 = new Box("deporte", "armario dormitorio");
        Box b3 = new Box("electrónica", "estantería oficina");

        session.persist(b1);
        session.persist(b2);
        session.persist(b3);


        // Create items
        Item i1 = new Item("Luces de árbol de navidad",
                "Luces LED multicolor de 2 metros, con control remoto para cambiar los modos de iluminación.");
        Item i2 = new Item("Bolas de navidad brillantes",
                "Set de 24 bolas de navidad en colores rojo, dorado y verde con acabado brillante y mate.");
        Item i3 = new Item("Estrella de punta de árbol",
                "Estrella luminosa para colocar en la punta del árbol de navidad, con luces LED integradas.");
        Item i4 = new Item("Calcetines navideños",
                "Par de calcetines tejidos con motivos navideños, perfectos para decorar la chimenea.");
        Item i5 = new Item("Set de figuras de belén",
                "Conjunto de 12 figuras de belén, incluyendo la Sagrada Familia, los Reyes Magos y animales del establo.");

        // Deporte
        Item i6 = new Item("Balón de fútbol",
                "Balón de fútbol tamaño oficial, diseñado para partidos y entrenamientos regulares.");
        Item i7 = new Item("Raqueta de tenis",
                "Raqueta de tenis ligera y resistente, con encordado de alta tensión para mejor control.");
        Item i8 = new Item("Esterilla de yoga",
                "Esterilla antideslizante de 4 mm de espesor, ideal para prácticas de yoga y pilates.");
        Item i9 = new Item("Guantes de boxeo",
                "Par de guantes de boxeo de cuero sintético, con acolchado para protección de nudillos.");
        Item i10 = new Item("Botella de agua deportiva",
                "Botella de agua de 750 ml con tapa de rosca y boquilla, libre de BPA.");

        // Electrónica
        Item i11 = new Item("Auriculares inalámbricos",
                "Auriculares Bluetooth con cancelación de ruido y estuche de carga portátil.");
        Item i12 = new Item("Smartwatch deportivo",
                "Reloj inteligente con seguimiento de actividad, notificaciones y resistencia al agua.");
        Item i13 = new Item("Cámara de acción",
                "Cámara de acción 4K a prueba de agua, ideal para capturar aventuras al aire libre.");
        Item i14 = new Item("Altavoz Bluetooth portátil",
                "Altavoz inalámbrico con 10 horas de batería y resistencia al agua IPX5.");
        Item i15 = new Item("Cargador portátil",
                "Batería externa de 10000mAh con dos puertos USB, para cargar dispositivos sobre la marcha.");

        session.persist(i1);
        i1.getCategories().add(c1); // Categoría Navidad
        i1.getCategories().add(c3); // Categoría Electrónica
        i1.setBox(b1); // Caja de Navidad

        session.persist(i2);
        i2.getCategories().add(c1);
        i2.setBox(b1);

        session.persist(i3);
        i3.getCategories().add(c1);
        i3.getCategories().add(c3);
        i3.setBox(b1);

        session.persist(i4);
        i4.getCategories().add(c1);
        i4.setBox(b1);

        session.persist(i5);
        i5.getCategories().add(c1);
        i5.setBox(b1);

        session.persist(i6);
        i6.getCategories().add(c2);
        i6.setBox(b2);

        session.persist(i7);
        i7.getCategories().add(c2);
        i7.setBox(b2);

        session.persist(i8);
        i8.getCategories().add(c2);
        i8.setBox(b2);

        session.persist(i9);
        i9.getCategories().add(c2);
        i9.setBox(b2);

        session.persist(i10);
        i10.getCategories().add(c2);
        i10.setBox(b2);

        session.persist(i11);
        i11.getCategories().add(c3);
        i11.setBox(b3);

        session.persist(i12);
        i12.getCategories().add(c3);
        i12.setBox(b3);

        session.persist(i13);
        i13.getCategories().add(c3);
        i13.setBox(b3);

        session.persist(i14);
        i14.getCategories().add(c3);
        i14.setBox(b3);

        session.persist(i15);
        i15.getCategories().add(c3);
        i15.setBox(b3);

        User u1 = new User("User 1");
        User u2 = new User("User 2");
        User u3 = new User("User 3");

        session.persist(u1);
        session.persist(u2);
        session.persist(u3);

        session.getTransaction().commit();

        session.close();
    }
}
