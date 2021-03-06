PGDMP     8                    u        
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
    public       postgres    false    183   e       I	          0    16412    produtos 
   TABLE DATA               R   COPY produtos (codproduto, nome, preco, marca, fornecedor, tipo, qtd) FROM stdin;
    public       postgres    false    182   &       �           2606    16427    cnpj 
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
       public         postgres    false    181    181            H	   H  x�5�AR�0���9 0BIvT�Q�v�ҭ�K�_��:ŋ�P�d�I���wX;�i��L�dɅ �
t��vƘ�<�/WX��@r|"�`;��c��2��{�Z�J��#.�"+v�u��c�.��ɘ�ӂ��|"R�bG���F5�el�����d�%Y�����s��םQ�YY��V9c!95$��{�J����R7R����NQ�?�H�f�0��rɷ�#8�v[�p�ZLLEИm`V��IS��%�d@�)�*7���u�3Z=/H�՟>&<c�ߐ���D��Z?��c*��s(�Y���(8�珞��p�pi�c�xK�(���      J	   �   x�%NK
�0]ON�I�6�U-�D]���(5�I��<ŋt���3�+f9&�c"�`��Zי��	=F|��<?�_!�q���l댆r�!<�f1R.�����P�(�ܟ�5���;P���U�[ϱ`�_���fy
$Y� �<��u��Z5�U}�4D�h�����_VB�/� >�      I	     x����N�0���S�ıZҤM��
�H�ʑK�����=)l�c��ql�k%Y)54&��vHkt#�]�݇C`2+�^��S��D��-���4�7l�Ԫ��f3��ϴެ�3���������	�*%Tf0��:K+��>�̄8��<�6��>M������v����'f!�X1��M7�p���Z�rQ��J0mo�o����:��~:�ޣVf�\�~^\�*}�I"�V9gJ�u|��i�X��$o�����Wc�1#�|>t��     