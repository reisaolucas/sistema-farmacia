PGDMP         )            
    u        
   bdfarmacia    9.5.4    9.5.4     M	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            N	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            O	           1262    16405 
   bdfarmacia    DATABASE     h   CREATE DATABASE bdfarmacia WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';
    DROP DATABASE bdfarmacia;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            P	           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    6            Q	           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6                        3079    12623    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            R	           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16406    clientes    TABLE     �   CREATE TABLE clientes (
    nome character varying,
    cpf character varying NOT NULL,
    endereco character varying,
    tel character varying,
    email character varying
);
    DROP TABLE public.clientes;
       public         postgres    false    6            �            1259    16418    fornecedores    TABLE     �   CREATE TABLE fornecedores (
    nome character varying,
    cnpj character varying NOT NULL,
    tel character varying,
    email character varying
);
     DROP TABLE public.fornecedores;
       public         postgres    false    6            �            1259    16412    produtos    TABLE     �   CREATE TABLE produtos (
    codproduto integer NOT NULL,
    nome character varying,
    preco double precision,
    marca character varying,
    fornecedor character varying,
    tipo character varying,
    qtd integer
);
    DROP TABLE public.produtos;
       public         postgres    false    6            H	          0    16406    clientes 
   TABLE DATA               <   COPY clientes (nome, cpf, endereco, tel, email) FROM stdin;
    public       postgres    false    181          J	          0    16418    fornecedores 
   TABLE DATA               7   COPY fornecedores (nome, cnpj, tel, email) FROM stdin;
    public       postgres    false    183   �       I	          0    16412    produtos 
   TABLE DATA               R   COPY produtos (codproduto, nome, preco, marca, fornecedor, tipo, qtd) FROM stdin;
    public       postgres    false    182   �       �           2606    16427    cnpj 
   CONSTRAINT     J   ALTER TABLE ONLY fornecedores
    ADD CONSTRAINT cnpj PRIMARY KEY (cnpj);
 ;   ALTER TABLE ONLY public.fornecedores DROP CONSTRAINT cnpj;
       public         postgres    false    183    183            �           2606    16429 
   codProduto 
   CONSTRAINT     T   ALTER TABLE ONLY produtos
    ADD CONSTRAINT "codProduto" PRIMARY KEY (codproduto);
 ?   ALTER TABLE ONLY public.produtos DROP CONSTRAINT "codProduto";
       public         postgres    false    182    182            �           2606    16425    cpf 
   CONSTRAINT     D   ALTER TABLE ONLY clientes
    ADD CONSTRAINT cpf PRIMARY KEY (cpf);
 6   ALTER TABLE ONLY public.clientes DROP CONSTRAINT cpf;
       public         postgres    false    181    181            H	   c   x��;
�0 �99EO ~k����8��/5�������q�`�M��.����
����q����x�Y73p<J�����>2�08W���O�!^�)A�ē6      J	   [   x��;� �z���D�E�z�'C�'o�Sό�fGi���	�T�Zr��ц�c��	��ޘI� *��V�K5��1^H'���?q>�      I	   }   x�35��062�0�t-N�/KTHIUpI�+I-�44�3�����+�σ�j0>D5������	g@QjqjQYbIfY>�����X������ͬ�R
>!.���险y�
�����9@�b���� �U*�     